package bco.bookingcar.infrastructure.secondary.adapter;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import bco.bookingcar.domain.booking.BookedCarCreatedEvent;
import bco.bookingcar.domain.ports.BookingCarEventsDispatcher;
import bco.bookingcar.domain.shared.Period;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class BookingCarEventsDispatcherAdapter implements BookingCarEventsDispatcher {
    @Override
    public boolean hasDispatchedEventWithCarCustomerPeriod(UUID idCar, UUID idCustomer, Period period) {
        return true;
    }

    @Override
    public void dispatch(List<BookedCarCreatedEvent> createdEvents) {
        // Not implemented
    }
}
