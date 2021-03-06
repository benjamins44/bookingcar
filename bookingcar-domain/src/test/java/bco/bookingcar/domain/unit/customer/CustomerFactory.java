package bco.bookingcar.domain.unit.customer;

import bco.bookingcar.domain.customer.Customer;

public interface CustomerFactory {
    static Customer build() {
        return Customer.builder()
                .firstname("unknown")
                .lastname("customer")
                .build();
    }
}
