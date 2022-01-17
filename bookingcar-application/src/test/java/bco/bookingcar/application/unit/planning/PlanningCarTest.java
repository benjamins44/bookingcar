package bco.bookingcar.application.unit.planning;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import bco.bookingcar.application.planning.BcoPlanningCarManager;
import bco.bookingcar.application.PlanningCarManager;
import bco.bookingcar.domain.ports.StoreBookedCar;
import bco.bookingcar.domain.ports.StoreCars;
import bco.bookingcar.domain.ports.StoreCustomers;
import bco.bookingcar.domain.shared.Period;
import bco.bookingcar.domain.unit.InjectDomainObjects;
import bco.bookingcar.domain.unit.booking.StoreBookedCarUtils;
import bco.bookingcar.domain.unit.car.CarFactory;
import bco.bookingcar.domain.unit.shared.PeriodFactory;

import static org.assertj.core.api.Assertions.assertThat;

@InjectDomainObjects
@DisplayName("Planning car manager test")
public class PlanningCarTest {

    private StoreCars storeCars;
    private StoreBookedCar storeBookedCar;
    private PlanningCarManager planningCarManager;

    @BeforeEach
    void setup(StoreCars storeCars, StoreBookedCar storeBookedCar, StoreCustomers storeCustomers) {
        this.storeCars = storeCars;
        this.storeBookedCar = storeBookedCar;
        planningCarManager = new BcoPlanningCarManager(storeCars, storeBookedCar, storeCustomers);
    }

    @Nested
    @DisplayName("Get planning of cars")
    class GetPlanningCarTest {
        @Test
        void can_get_planning_of_one_car_without_booked() {
            var nbOfCars = 1;
            storeCars.addAll(CarFactory.buildCars(nbOfCars));

            var planningCars = planningCarManager.get(PeriodFactory.build());

            assertThat(planningCars.size()).isEqualTo(1);
            assertThat(planningCars.get(0).getPlanningBookedCar().isEmpty()).isTrue();
        }

        @Test
        void can_get_planning_of_one_car_with_one_booked() {
            var nbOfCars = 1;
            var period = PeriodFactory.build();
            var cars = storeCars.addAll(CarFactory.buildCars(nbOfCars));
            StoreBookedCarUtils.changeAndSaveBookedPeriodOfCars(cars,
                    List.of(Period.builder()
                            .startDateTime(period.getStartDateTime().minusHours(2L))
                            .endDateTime(period.getStartDateTime().plusHours(1L))
                            .build()),
                    storeBookedCar
            );

            var planningCars = planningCarManager.get(period);

            assertThat(planningCars.size()).isEqualTo(1);
            assertThat(planningCars.get(0).getPlanningBookedCar().size()).isEqualTo(1);
        }

        @Test
        void can_get_planning_of_two_cars_with_one_booked_on_each() {
            var nbOfCars = 2;
            var period = PeriodFactory.build();
            var cars = storeCars.addAll(CarFactory.buildCars(nbOfCars));
            StoreBookedCarUtils.changeAndSaveBookedPeriodOfCars(cars,
                    List.of(Period.builder()
                                    .startDateTime(period.getStartDateTime().minusHours(2L))
                                    .endDateTime(period.getStartDateTime().plusHours(1L))
                                    .build(),
                            Period.builder()
                                    .startDateTime(period.getEndDateTime().minusHours(2L))
                                    .endDateTime(period.getEndDateTime().plusHours(1L))
                                    .build()
                    ),
                    storeBookedCar
            );

            var planningCars = planningCarManager.get(period);

            assertThat(planningCars.size()).isEqualTo(2);
            assertThat(planningCars.get(0).getPlanningBookedCar().size()).isEqualTo(1);
            assertThat(planningCars.get(1).getPlanningBookedCar().size()).isEqualTo(1);
        }
    }

}
