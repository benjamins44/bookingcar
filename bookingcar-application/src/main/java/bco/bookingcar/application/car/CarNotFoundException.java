package bco.bookingcar.application.car;

import bco.bookingcar.exceptions.BookingCarException;

import java.util.UUID;

public class CarNotFoundException extends BookingCarException {
    public CarNotFoundException(final UUID idCar) {
        super(String.format("The car with id %s is not found", idCar.toString()));
    }
}
