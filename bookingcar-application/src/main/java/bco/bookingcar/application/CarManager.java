package bco.bookingcar.application;

import bco.bookingcar.application.car.CarNotFoundException;
import bco.bookingcar.domain.car.Car;

import java.util.UUID;

public interface CarManager {
    Car findById(UUID carId) throws CarNotFoundException;
}
