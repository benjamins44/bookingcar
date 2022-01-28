package bco.bookingcar.domain.booking;

import bco.bookingcar.exceptions.BookingCarException;

import java.util.UUID;

public class CarNotAvailableException extends BookingCarException {
    public CarNotAvailableException(final UUID idCar) {
        super(String.format("The car with id %s is not avalaible", idCar.toString()));
    }
}
