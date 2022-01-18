package bco.bookingcar.domain.ports;

import java.util.List;
import java.util.UUID;

import bco.bookingcar.domain.booking.BookedCarCreatedEvent;
import bco.bookingcar.domain.shared.Period;

public interface BookingCarEventsDispatcher {
    boolean hasDispatchedEventWithCarCustomerPeriod(UUID idCar, UUID idCustomer, Period period);

    void dispatch(List<BookedCarCreatedEvent> createdEvents);
}
