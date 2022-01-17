package bco.bookingcar.application.unit.booking;

import java.util.List;
import java.util.UUID;

import bco.bookingcar.application.booking.AvailableCar;
import bco.bookingcar.application.planning.PlanningCar;
import bco.bookingcar.domain.unit.car.CarFactory;
import bco.bookingcar.domain.unit.shared.PeriodFactory;

public interface PlanningCarFactory {
    static PlanningCar build() {
        return PlanningCar.builder()
                .car(CarFactory.build())
                .planningBookedCar(List.of())
                .build();
    }
}
