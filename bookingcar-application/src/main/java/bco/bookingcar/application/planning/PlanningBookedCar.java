package bco.bookingcar.application.planning;

import bco.bookingcar.annotation.DTO;
import bco.bookingcar.domain.customer.Customer;
import bco.bookingcar.domain.shared.Period;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.With;

import static org.apache.commons.lang3.Validate.notNull;

@With
@Builder
@Getter
@ToString
@DTO
public class PlanningBookedCar {
    private Customer customer;
    private Period period;

    public PlanningBookedCar(Customer customer, Period period) {
        notNull(customer, "The customer is mandatory");
        notNull(period, "The period is mandatory");

        this.customer = customer;
        this.period = period;
    }
}
