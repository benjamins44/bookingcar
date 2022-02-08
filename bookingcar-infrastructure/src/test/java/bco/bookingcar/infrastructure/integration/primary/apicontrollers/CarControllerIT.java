package bco.bookingcar.infrastructure.integration.primary.apicontrollers;

import bco.bookingcar.application.GetCarUseCase;
import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.unit.car.CarFactory;
import bco.bookingcar.infrastructure.integration.primary.configuration.ApplicationConfigurationTest;
import bco.bookingcar.infrastructure.integration.primary.stubs.CarManagerStub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({ApplicationConfigurationTest.class})
@WebMvcTest
public class CarControllerIT {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private GetCarUseCase carManager;

    @BeforeEach
    void setup() {
        this.setCarToManager(null);
    }

    @Test
    void can_get_car_by_id() throws Exception {
        var car = CarFactory.build().withId(UUID.randomUUID());
        this.setCarToManager(car);

        this.mvc.perform(
                        get(String.format("/cars/%s", car.getId()))
                                .accept(APPLICATION_JSON)
                                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(car.getId().toString())))
                .andExpect(jsonPath("$.brand", is(car.getBrand())))
                .andExpect(jsonPath("$.model", is(car.getModel())))
                .andExpect(jsonPath("$.category", is(car.getCategory().name())))
                .andExpect(jsonPath("$.numberOfPlace", is(car.getNumberOfPlace())));
    }

    @Test
    void receive_not_found_status_if_car_not_found() throws Exception {
        var idCar = UUID.randomUUID();
        this.mvc.perform(
                        get(String.format("/cars/%s", idCar))
                                .accept(APPLICATION_JSON)
                                .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error.code", is(String.valueOf(HttpStatus.NOT_FOUND.value()))))
                .andExpect(jsonPath("$.error.message", is(String.format("The car %s does not exist.", idCar))))
                .andExpect(jsonPath("$.error.errors[0].message", is("error.car-not-found")));
    }

    private void setCarToManager(Car car) {
        ((CarManagerStub) carManager).setCar(car);
    }
}
