package bco.bookingcar.domain.booking;

import bco.bookingcar.annotation.ValueObject;
import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.customer.Customer;
import bco.bookingcar.domain.shared.Period;
import lombok.*;

import static org.apache.commons.lang3.Validate.notNull;

@With
@Builder
@Getter
@ToString
@EqualsAndHashCode
@ValueObject
public class BookingCarAttempt {
    private Customer customer;
    private Car car;
    private Period period;

    public BookingCarAttempt(Customer customer, Car car, Period period) {
        notNull(customer, "The customer is mandatory");
        notNull(car, "The car is mandatory");
        notNull(period, "The period is mandatory");

        this.car = car;
        this.period = period;
        this.customer = customer;
    }


}
