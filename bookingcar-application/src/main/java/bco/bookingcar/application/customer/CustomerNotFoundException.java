package bco.bookingcar.application.customer;

import bco.bookingcar.domain.shared.BookingMessage;
import bco.bookingcar.exceptions.BusinessException;

import java.util.UUID;

public class CustomerNotFoundException extends BusinessException {
    public CustomerNotFoundException(final UUID idCustomer) {
        super(BusinessException.builder()
                .message(BookingMessage.CUSTOMER_NOT_FOUND)
                .argument("idCustomer", idCustomer)
        );
    }
}
