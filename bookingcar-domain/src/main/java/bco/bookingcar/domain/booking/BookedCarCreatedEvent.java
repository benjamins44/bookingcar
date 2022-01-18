package bco.bookingcar.domain.booking;

import java.util.UUID;

import bco.bookingcar.annotation.DomainEvent;
import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.customer.Customer;
import bco.bookingcar.domain.shared.Period;
import lombok.Builder;
import lombok.Getter;
import lombok.With;

import static org.apache.commons.lang3.Validate.notNull;

@Builder
@Getter
@With
@DomainEvent
public class BookedCarCreatedEvent {
    private UUID id;
    private Car car;
    private Customer customer;
    private Period period;

    public BookedCarCreatedEvent(UUID id, Car car, Customer customer, Period period) {
        notNull(customer, "The customer is mandatory");
        notNull(car, "The car is mandatory");
        notNull(period, "The startDate is mandatory");

        this.id = id;
        this.car = car;
        this.customer = customer;
        this.period = period;
    }
}
