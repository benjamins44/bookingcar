package bco.bookingcar.primary;

import bco.bookingcar.customer.CustomerNotFoundException;
import bco.bookingcar.domain.customer.Customer;

import java.util.UUID;

public interface CustomerManager {
    Customer findById(UUID customerId) throws CustomerNotFoundException;
}
