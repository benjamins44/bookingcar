package bco.bookingcar.application.customer;

import bco.bookingcar.annotation.DTO;
import bco.bookingcar.domain.customer.Customer;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@DTO
public class GetCustomerResponse {
    private Customer customer;
}
