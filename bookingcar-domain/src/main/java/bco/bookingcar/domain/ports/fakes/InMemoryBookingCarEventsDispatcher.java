package bco.bookingcar.domain.ports.fakes;

import bco.bookingcar.annotation.Fake;
import bco.bookingcar.domain.booking.BookedCarCreatedEvent;
import bco.bookingcar.domain.ports.BookingCarEventsDispatcher;
import bco.bookingcar.domain.shared.Period;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@Fake
public class InMemoryBookingCarEventsDispatcher implements BookingCarEventsDispatcher {

    private final List<BookedCarCreatedEvent> events = new ArrayList<>();

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
