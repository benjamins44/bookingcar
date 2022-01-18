package bco.bookingcar.infrastructure.secondary.adapter;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import bco.bookingcar.domain.booking.BookedCarCreatedEvent;
import bco.bookingcar.domain.ports.BookingCarEventsDispatcher;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class BookingCarEventsDispatcherAdapter implements BookingCarEventsDispatcher {
    private final ApplicationEventPublisher publisher;

    @Override
    public void dispatch(List<BookedCarCreatedEvent> createdEvents) {
        createdEvents.forEach(publisher::publishEvent);
    }
}
