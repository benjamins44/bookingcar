package bco.bookingcar.domain.unit.booking;

import bco.bookingcar.domain.booking.AvailableCar;
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
