package bco.bookingcar.domain.shared;

import bco.bookingcar.exceptions.MessageException;

public enum BookingMessage implements MessageException {
    CAR_NOT_AVAILABLE("error.car-unavailable"),
    CAR_NOT_FOUND("error.car-not-found"),
    START_DATE_AFTER_END_DATE("error.start-date-after-end-date"),
    CUSTOMER_NOT_FOUND("error.customer-not-found");

    private final String messageKey;

    BookingMessage(String code) {
        this.messageKey = code;
    }

    @Override
    public String getMessageKey() {
        return messageKey;
    }
}
