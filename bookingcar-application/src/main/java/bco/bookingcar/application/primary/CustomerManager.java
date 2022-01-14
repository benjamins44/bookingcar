package bco.bookingcar.application.primary;

import bco.bookingcar.application.customer.CustomerNotFoundException;
import bco.bookingcar.domain.customer.Customer;

import java.util.UUID;

public interface CustomerManager {
    Customer findById(UUID customerId) throws CustomerNotFoundException;
}
