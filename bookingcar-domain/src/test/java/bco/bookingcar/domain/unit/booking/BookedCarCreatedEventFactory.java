package bco.bookingcar.domain.unit.booking;

import java.util.UUID;

import bco.bookingcar.domain.booking.BookedCarCreatedEvent;
import bco.bookingcar.domain.unit.car.CarFactory;
import bco.bookingcar.domain.unit.customer.CustomerFactory;
import bco.bookingcar.domain.unit.shared.PeriodFactory;

public interface BookedCarCreatedEventFactory {
    static BookedCarCreatedEvent build() {
        return BookedCarCreatedEvent.builder()
                .customer(CustomerFactory.build().withId(UUID.randomUUID()))
                .car(CarFactory.build().withId(UUID.randomUUID()))
                .period(PeriodFactory.build())
                .build();
    }
}
