package bco.bookingcar.infrastructure.integration.primary.fakes;

import bco.bookingcar.application.CarManager;
import bco.bookingcar.application.car.CarNotFoundException;
import bco.bookingcar.domain.car.Car;

import java.util.Optional;
import java.util.UUID;

public class CarManagerFake implements CarManager {
    private Optional<Car> car = Optional.empty();

    @Override
    public Car findById(UUID carId) throws CarNotFoundException {
        return car.orElseThrow(() -> new CarNotFoundException(carId));
    }

    public void setCar(Car car) {
        this.car = car == null ? Optional.empty() : Optional.of(car);
    }
}
