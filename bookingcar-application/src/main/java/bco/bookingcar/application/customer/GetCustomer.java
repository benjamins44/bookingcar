package bco.bookingcar.application.customer;

import bco.bookingcar.domain.customer.Customer;

import java.util.UUID;

public interface GetCustomer {
    Customer findById(UUID customerId) throws CustomerNotFoundException;
}
