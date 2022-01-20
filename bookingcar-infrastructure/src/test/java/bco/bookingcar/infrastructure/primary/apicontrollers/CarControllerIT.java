package bco.bookingcar.infrastructure.primary.apicontrollers;

import bco.bookingcar.application.CarManager;
import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.unit.car.CarFactory;
import bco.bookingcar.infrastructure.ReservationVoituresApplication;
import bco.bookingcar.infrastructure.primary.configuration.ApplicationConfigurationTest;
import bco.bookingcar.infrastructure.primary.fakes.CarManagerFake;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import({
        ReservationVoituresApplication.class,
        ApplicationConfigurationTest.class
})
public class CarControllerIT {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private CarManager carManager;

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
    void receive_bad_request_if_car_not_found() throws Exception {
        this.mvc.perform(
                        get(String.format("/cars/%s", UUID.randomUUID()))
                                .accept(APPLICATION_JSON)
                                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    private void setCarToManager(Car car) {
        ((CarManagerFake) carManager).setCar(car);
    }
}
