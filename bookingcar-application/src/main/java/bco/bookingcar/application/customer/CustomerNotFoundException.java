package bco.bookingcar.application.customer;

import bco.bookingcar.exceptions.BookingCarException;

import java.util.UUID;

public class CustomerNotFoundException extends BookingCarException {
    public CustomerNotFoundException(final UUID idCustomer) {
        super(String.format("The customer with id %s is not found", idCustomer.toString()));
    }
}
