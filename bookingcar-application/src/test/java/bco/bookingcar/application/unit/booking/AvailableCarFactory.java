package bco.bookingcar.application.unit.booking;

import bco.bookingcar.application.booking.AvailableCar;
import bco.bookingcar.domain.unit.shared.PeriodFactory;

import java.util.UUID;

public interface AvailableCarFactory {
    static AvailableCar build() {
        return AvailableCar.builder()
                .idCar(UUID.randomUUID())
                .period(PeriodFactory.build())
                .build();
    }
}
