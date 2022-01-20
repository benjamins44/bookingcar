package bco.bookingcar.application.unit.booking;

import bco.bookingcar.application.booking.AvailableCar;
import bco.bookingcar.domain.unit.car.CarFactory;
import bco.bookingcar.domain.unit.shared.PeriodFactory;

public interface AvailableCarFactory {
    static AvailableCar build() {
        return AvailableCar.builder()
                .car(CarFactory.build())
                .period(PeriodFactory.build())
                .build();
    }
}
