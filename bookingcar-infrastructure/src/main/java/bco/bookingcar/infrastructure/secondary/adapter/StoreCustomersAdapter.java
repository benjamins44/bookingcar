package bco.bookingcar.infrastructure.secondary.adapter;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import bco.bookingcar.domain.customer.Customer;
import bco.bookingcar.domain.ports.StoreCustomers;
import bco.bookingcar.infrastructure.secondary.entities.CustomerEntity;
import bco.bookingcar.infrastructure.secondary.repositories.CustomerRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class StoreCustomersAdapter implements StoreCustomers {

    private CustomerRepository customerRepository;

    @Override
    public Customer add(Customer customer) {
        return customerRepository.save(CustomerEntity.fromDomain(customer)).toDomain();
    }

    @Override
    public Optional<Customer> getById(UUID customerId) {
        var optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(optionalCustomer.get().toDomain());
    }
}
