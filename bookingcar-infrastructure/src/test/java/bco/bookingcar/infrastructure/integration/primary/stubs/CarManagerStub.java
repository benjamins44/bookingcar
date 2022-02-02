package bco.bookingcar.infrastructure.integration.primary.stubs;

import bco.bookingcar.annotation.Stub;
import bco.bookingcar.application.CarManager;
import bco.bookingcar.application.car.CarNotFoundException;
import bco.bookingcar.domain.car.Car;

import java.util.Optional;
import java.util.UUID;

@Stub
public class CarManagerStub implements CarManager {
    private Optional<Car> car = Optional.empty();

    @Override
    public Car findById(UUID carId) throws CarNotFoundException {
        return car.orElseThrow(() -> new CarNotFoundException(carId));
    }

    public void setCar(Car car) {
        this.car = car == null ? Optional.empty() : Optional.of(car);
    }
}
