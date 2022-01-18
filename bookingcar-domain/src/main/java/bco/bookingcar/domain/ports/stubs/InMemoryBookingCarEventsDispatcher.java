package bco.bookingcar.domain.ports.stubs;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import bco.bookingcar.annotation.Stub;
import bco.bookingcar.domain.booking.BookedCarCreatedEvent;
import bco.bookingcar.domain.ports.BookingCarEventsDispatcher;
import bco.bookingcar.domain.shared.Period;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@Stub
public class InMemoryBookingCarEventsDispatcher implements BookingCarEventsDispatcher {

    private final List<BookedCarCreatedEvent> events = new ArrayList<>();

    @Override
    public boolean hasDispatchedEventWithCarCustomerPeriod(UUID idCar, UUID idCustomer, Period period) {
        return events.stream()
                .anyMatch(event -> idCar.equals(event.getCar().getId())
                        && idCustomer.equals(event.getCustomer().getId())
                        && period.equals(event.getPeriod())
                );
    }

    @Override
    public void dispatch(List<BookedCarCreatedEvent> createdEvents) {
        events.addAll(createdEvents);
    }
}
