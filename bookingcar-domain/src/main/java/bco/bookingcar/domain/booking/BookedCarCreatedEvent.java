package bco.bookingcar.domain.booking;

import bco.bookingcar.annotation.DomainEvent;
import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.customer.Customer;
import bco.bookingcar.domain.shared.Period;
import bco.bookingcar.exceptions.BusinessException;
import lombok.Builder;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.With;

import java.util.UUID;

import static bco.bookingcar.validation.Assert.field;

@Builder
@Getter
@With
@DomainEvent
public class BookedCarCreatedEvent {
    private UUID id;
    private Car car;
    private Customer customer;
    private Period period;

    @SneakyThrows({BusinessException.class})
    public BookedCarCreatedEvent(UUID id, Car car, Customer customer, Period period) {
        field("customer", customer).notNull();
        field("car", car).notNull();
        field("period", period).notNull();

        this.id = UUID.randomUUID();
        this.car = car;
        this.customer = customer;
        this.period = period;
    }
}
