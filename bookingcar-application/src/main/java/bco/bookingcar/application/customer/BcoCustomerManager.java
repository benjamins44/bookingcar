package bco.bookingcar.application.customer;

import bco.bookingcar.annotation.ApplicationService;
import bco.bookingcar.application.CustomerManager;
import bco.bookingcar.domain.customer.Customer;
import bco.bookingcar.domain.ports.StoreCustomers;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@ApplicationService
public class BcoCustomerManager implements CustomerManager {
    private StoreCustomers storeCustomers;

    @Override
    public Customer findById(UUID customerId) throws CustomerNotFoundException {
        return storeCustomers.getById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
    }
}
