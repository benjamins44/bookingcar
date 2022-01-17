package bco.bookingcar.application.unit.booking;

import java.util.List;

import bco.bookingcar.application.planning.PlanningBookedCar;
import bco.bookingcar.application.planning.PlanningCar;
import bco.bookingcar.domain.unit.car.CarFactory;
import bco.bookingcar.domain.unit.customer.CustomerFactory;
import bco.bookingcar.domain.unit.shared.PeriodFactory;

public interface PlanningBookedCarFactory {
    static PlanningBookedCar build() {
        return PlanningBookedCar.builder()
                .period(PeriodFactory.build())
                .customer(CustomerFactory.build())
                .build();
    }
}
