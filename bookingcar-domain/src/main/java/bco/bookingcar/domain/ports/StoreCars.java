package bco.bookingcar.domain.ports;

import bco.bookingcar.domain.car.Car;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StoreCars {
    Car add(Car car);

    List<Car> addAll(List<Car> buildCars);

    List<Car> getAll();

    void saveAll(List<Car> car1);

    Optional<Car> getById(UUID carId);
}
