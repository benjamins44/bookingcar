package bco.bookingcar.infrastructure.integration.primary.apicontrollers;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import bco.bookingcar.application.GetPlanningCarUseCase;
import bco.bookingcar.application.planning.PlanningCar;
import bco.bookingcar.application.unit.booking.PlanningBookedCarFactory;
import bco.bookingcar.domain.unit.car.CarFactory;
import bco.bookingcar.domain.unit.shared.PeriodFactory;
import bco.bookingcar.infrastructure.integration.primary.configuration.ApplicationConfigurationTest;
import bco.bookingcar.infrastructure.integration.primary.stubs.GetPlanningCarUseCaseStub;
import bco.bookingcar.utils.ZonedDateUtils;

import static org.hamcrest.Matchers.containsString;
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
    private GetPlanningCarUseCase getPlanningCarUseCase;

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
                .andExpect(jsonPath("$.result", hasSize(0)));
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
                .andExpect(jsonPath("$.result", hasSize(1)));
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
                .andExpect(jsonPath("$.result", hasSize(1)))
                .andExpect(jsonPath("$.result[0].planningBookedCar", hasSize(2)));
    }

    @Test
    void has_self_link() throws Exception {
        var period = PeriodFactory.build();
        var linkCurrentPeriod = String.format("/planning?startDateTime=%s&endDateTime=%s", ZonedDateUtils.toString(period.getStartDateTime()), ZonedDateUtils.toString(period.getEndDateTime()));

        this.mvc.perform(
                        get(linkCurrentPeriod)
                                .accept(APPLICATION_JSON)
                                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links.self.href", containsString(linkCurrentPeriod)));
    }

    @Test
    void has_next_link_with_each_date_plus_one_day() throws Exception {
        var period = PeriodFactory.build();
        var linkCurrentPeriod = String.format("/planning?startDateTime=%s&endDateTime=%s", ZonedDateUtils.toString(period.getStartDateTime()), ZonedDateUtils.toString(period.getEndDateTime()));
        var linkNextDayPeriod = String.format("/planning?startDateTime=%s&endDateTime=%s", ZonedDateUtils.toString(period.getStartDateTime().plusDays(1L)), ZonedDateUtils.toString(period.getEndDateTime().plusDays(1L)));

        this.mvc.perform(
                        get(linkCurrentPeriod)
                                .accept(APPLICATION_JSON)
                                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links.nextDay.href", containsString(linkNextDayPeriod)));
    }

    @Test
    void has_previous_link_with_each_date_minus_one_day() throws Exception {
        var period = PeriodFactory.build();
        var linkCurrentPeriod = String.format("/planning?startDateTime=%s&endDateTime=%s", ZonedDateUtils.toString(period.getStartDateTime()), ZonedDateUtils.toString(period.getEndDateTime()));
        var linkPreviousDayPeriod = String.format("/planning?startDateTime=%s&endDateTime=%s", ZonedDateUtils.toString(period.getStartDateTime().minusDays(1L)), ZonedDateUtils.toString(period.getEndDateTime().minusDays(1L)));

        this.mvc.perform(
                        get(linkCurrentPeriod)
                                .accept(APPLICATION_JSON)
                                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links.previousDay.href", containsString(linkPreviousDayPeriod)));
    }

    private void setPlanningToManager(List<PlanningCar> plannings) {
        ((GetPlanningCarUseCaseStub) getPlanningCarUseCase).setPlanning(plannings);
    }
}
