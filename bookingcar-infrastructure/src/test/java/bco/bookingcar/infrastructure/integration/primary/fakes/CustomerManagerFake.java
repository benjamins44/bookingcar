package bco.bookingcar.infrastructure.integration.primary.fakes;

import bco.bookingcar.application.CustomerManager;
import bco.bookingcar.application.customer.CustomerNotFoundException;
import bco.bookingcar.domain.customer.Customer;

import java.util.Optional;
import java.util.UUID;

public class CustomerManagerFake implements CustomerManager {
    private Optional<Customer> customer = Optional.empty();

    @Override
    public Customer findById(UUID customerId) throws CustomerNotFoundException {
        return customer.orElseThrow(CustomerNotFoundException::new);
    }

    public void setCustomer(Customer customer) {
        this.customer = customer == null ? Optional.empty() : Optional.of(customer);
    }
}
