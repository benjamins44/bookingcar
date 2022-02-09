package bco.bookingcar.application.car;

import java.util.UUID;

import bco.bookingcar.domain.car.Car;

public interface GetCar {
    Car findById(UUID carId) throws CarNotFoundException;
}
