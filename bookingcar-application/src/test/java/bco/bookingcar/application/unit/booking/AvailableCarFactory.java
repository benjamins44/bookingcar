package bco.bookingcar.application.unit.booking;

import java.util.UUID;

import bco.bookingcar.application.booking.AvailableCar;
import bco.bookingcar.domain.unit.car.CarFactory;
import bco.bookingcar.domain.unit.shared.PeriodFactory;

public interface AvailableCarFactory {
    static AvailableCar build() {
        return AvailableCar.builder()
                .car(CarFactory.build().withId(UUID.randomUUID()))
                .period(PeriodFactory.build())
                .build();
    }
}
