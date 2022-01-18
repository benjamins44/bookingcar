package bco.bookingcar.domain.ports;

import java.util.List;

import bco.bookingcar.domain.booking.BookedCarCreatedEvent;

public interface BookingCarEventsDispatcher {
    void dispatch(List<BookedCarCreatedEvent> createdEvents);
}
