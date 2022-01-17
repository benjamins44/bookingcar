package bco.bookingcar.domain.secondary;

import bco.bookingcar.domain.customer.Customer;

import java.util.Optional;
import java.util.UUID;

public interface StoreCustomers {
    Customer add(Customer customer);

    Optional<Customer> getById(UUID fromString);
}
