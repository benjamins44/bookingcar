package bco.bookingcar.domain.unit.booking;

import bco.bookingcar.domain.booking.BookedCar;
import bco.bookingcar.domain.unit.shared.PeriodFactory;

import java.util.UUID;

public interface BookedCarFactory {
    static BookedCar build() {
        return BookedCar.builder()
                .id(null)
                .idCustomer(UUID.randomUUID())
                .idCar(UUID.randomUUID())
                .period(PeriodFactory.build())
                .build();
    }
}
