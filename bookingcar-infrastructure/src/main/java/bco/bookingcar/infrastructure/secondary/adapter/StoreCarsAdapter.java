package bco.bookingcar.infrastructure.secondary.adapter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.ports.StoreCars;
import bco.bookingcar.infrastructure.secondary.entities.CarEntity;
import bco.bookingcar.infrastructure.secondary.repositories.CarRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class StoreCarsAdapter implements StoreCars {

    private CarRepository carRepository;

    @Override
    public Car add(Car car) {
        return carRepository.save(CarEntity.fromDomain(car)).toDomain();
    }

    @Override
    public List<Car> addAll(List<Car> cars) {
        return carRepository.saveAll(CarEntity.fromDomain(cars)).stream().map(CarEntity::toDomain).toList();
    }

    @Override
    public List<Car> getAll() {
        return carRepository.findAll().stream().map(CarEntity::toDomain).toList();
    }

    @Override
    public List<Car> saveAll(List<Car> cars) {
        return this.addAll(cars);
    }

    @Override
    public Optional<Car> getById(UUID carId) {
        var optionalCar = carRepository.findById(carId);
        if (optionalCar.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(optionalCar.get().toDomain());
    }
}
