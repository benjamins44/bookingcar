package bco.bookingcar.customer;

import bco.bookingcar.annotation.ApplicationService;
import bco.bookingcar.domain.customer.Customer;
import bco.bookingcar.domain.ports.StoreCustomers;
import bco.bookingcar.primary.CustomerManager;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@ApplicationService
public class BcoCustomerManager implements CustomerManager {
    private StoreCustomers storeCustomers;

    @Override
    public Customer findById(UUID carId) throws CustomerNotFoundException {
        return storeCustomers.getById(carId).orElseThrow(CustomerNotFoundException::new);
    }
}
