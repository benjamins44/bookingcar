package bco.bookingcar.domain.ports.fakes;

import bco.bookingcar.annotation.Fake;
import bco.bookingcar.domain.customer.Customer;
import bco.bookingcar.domain.ports.StoreCustomers;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Builder
@NoArgsConstructor
@Fake
public class InMemoryStoreCustomers implements StoreCustomers {

    private final Map<UUID, Customer> customerMap = new HashMap<>();

    @Override
    public Customer add(Customer customer) {
        var saveCustomer = customer.getId() == null ? customer.withId(UUID.randomUUID()) : customer;
        customerMap.put(saveCustomer.getId(), saveCustomer);
        return saveCustomer;
    }

    @Override
    public Optional<Customer> getById(UUID id) {
        final var customer = customerMap.get(id);
        if (customer == null) {
            return Optional.empty();
        }
        return Optional.of(customer);
    }
}
