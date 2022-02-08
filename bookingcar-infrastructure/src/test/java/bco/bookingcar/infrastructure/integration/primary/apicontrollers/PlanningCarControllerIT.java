package bco.bookingcar.infrastructure.integration.primary.apicontrollers;

import bco.bookingcar.application.PlanningCarManager;
import bco.bookingcar.application.planning.PlanningCar;
import bco.bookingcar.application.unit.booking.PlanningBookedCarFactory;
import bco.bookingcar.domain.unit.car.CarFactory;
import bco.bookingcar.domain.unit.shared.PeriodFactory;
import bco.bookingcar.infrastructure.integration.primary.configuration.ApplicationConfigurationTest;
import bco.bookingcar.infrastructure.integration.primary.stubs.PlanningCarManagerStub;
import bco.bookingcar.infrastructure.primary.apicontrollers.PlanningCarController;
import bco.bookingcar.utils.ZonedDateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({ApplicationConfigurationTest.class})
@WebMvcTest
public class PlanningCarControllerIT {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private PlanningCarManager planningCarManager;

    @BeforeEach
    void setup() {
        this.setPlanningToManager(List.of());
    }

    @Test
    void can_get_empty_planning() throws Exception {
        var period = PeriodFactory.build();

        this.mvc.perform(
                        get(String.format("/planning?startDateTime=%s&endDateTime=%s", ZonedDateUtils.toString(period.getStartDateTime()), ZonedDateUtils.toString(period.getEndDateTime())))
                                .accept(APPLICATION_JSON)
                                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void can_get_one_car_without_planning() throws Exception {
        var period = PeriodFactory.build();
        setPlanningToManager(List.of(
                        PlanningCar.builder()
                                .car(CarFactory.build())
                                .planningBookedCar(List.of())
                                .build()
                )
        );

        this.mvc.perform(
                        get(String.format("/planning?startDateTime=%s&endDateTime=%s", ZonedDateUtils.toString(period.getStartDateTime()), ZonedDateUtils.toString(period.getEndDateTime())))
                                .accept(APPLICATION_JSON)
                                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void can_get_one_car_with_planning() throws Exception {
        var period = PeriodFactory.build();
        setPlanningToManager(List.of(
                        PlanningCar.builder()
                                .car(CarFactory.build())
                                .planningBookedCar(List.of(
                                        PlanningBookedCarFactory.build(),
                                        PlanningBookedCarFactory.build()
                                ))
                                .build()
                )
        );

        this.mvc.perform(
                        get(String.format("/planning?startDateTime=%s&endDateTime=%s", ZonedDateUtils.toString(period.getStartDateTime()), ZonedDateUtils.toString(period.getEndDateTime())))
                                .accept(APPLICATION_JSON)
                                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].planningBookedCar", hasSize(2)));
    }

    private void setPlanningToManager(List<PlanningCar> plannings) {
        ((PlanningCarManagerStub) planningCarManager).setPlanning(plannings);
    }
}
