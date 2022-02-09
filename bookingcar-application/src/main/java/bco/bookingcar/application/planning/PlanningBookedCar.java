package bco.bookingcar.application.planning;

import bco.bookingcar.annotation.DTO;
import bco.bookingcar.domain.customer.Customer;
import bco.bookingcar.domain.shared.Period;
import bco.bookingcar.exceptions.BusinessException;
import lombok.Builder;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.With;

import static bco.bookingcar.validation.Assert.field;
import static org.apache.commons.lang3.Validate.notNull;

@With
@Builder
@Getter
@ToString
@DTO
public class PlanningBookedCar {
    private Customer customer;
    private Period period;

    @SneakyThrows({ BusinessException.class})
    public PlanningBookedCar(Customer customer, Period period) {
        field("customer", customer).notNull();
        field("period", period).notNull();

        this.customer = customer;
        this.period = period;
    }
}
