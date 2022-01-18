package bco.bookingcar.domain.ports;

import bco.bookingcar.annotation.DomainRepository;
import bco.bookingcar.domain.customer.Customer;

import java.util.Optional;
import java.util.UUID;

@DomainRepository
public interface StoreCustomers {
    Customer add(Customer customer);

    Optional<Customer> getById(UUID fromString);
}
