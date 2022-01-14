package bco.bookingcar.domain.unit.booking;

import bco.bookingcar.domain.booking.BookingCarAttempt;
import bco.bookingcar.domain.unit.car.CarFactory;
import bco.bookingcar.domain.unit.customer.CustomerFactory;
import bco.bookingcar.domain.unit.shared.PeriodFactory;

import java.util.UUID;

public interface BookingCarAttemptFactory {
    static BookingCarAttempt build() {
        return BookingCarAttempt.builder()
                .customer(CustomerFactory.build().withId(UUID.randomUUID()))
                .car(CarFactory.build().withId(UUID.randomUUID()))
                .period(PeriodFactory.build())
                .build();
    }
}
