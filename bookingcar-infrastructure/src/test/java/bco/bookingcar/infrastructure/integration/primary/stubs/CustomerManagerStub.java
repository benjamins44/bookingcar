package bco.bookingcar.infrastructure.integration.primary.stubs;

import bco.bookingcar.annotation.Stub;
import bco.bookingcar.application.CustomerManager;
import bco.bookingcar.application.customer.CustomerNotFoundException;
import bco.bookingcar.domain.customer.Customer;

import java.util.Optional;
import java.util.UUID;

@Stub
public class CustomerManagerStub implements CustomerManager {
    private Optional<Customer> customer = Optional.empty();

    @Override
    public Customer findById(UUID customerId) throws CustomerNotFoundException {
        return customer.orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    public void setCustomer(Customer customer) {
        this.customer = customer == null ? Optional.empty() : Optional.of(customer);
    }
}
