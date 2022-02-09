package bco.bookingcar.domain.ports.fakes;

import bco.bookingcar.annotation.Fake;
import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.ports.StoreCars;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@Fake
public class InMemoryStoreCars implements StoreCars {

    private final Map<UUID, Car> carMap = new HashMap<>();

    @Override
    public Car add(Car car) {
        var saveCar = car.getId() == null ? car.withId(UUID.randomUUID()) : car;
        carMap.put(saveCar.getId(), saveCar);
        return saveCar;
    }

    @Override
    public List<Car> addAll(List<Car> buildCars) {
        return buildCars.stream().map(this::add).toList();
    }

    @Override
    public List<Car> getAll() {
        return List.copyOf(carMap.values());
    }

    @Override
    public List<Car> saveAll(List<Car> cars) {
        cars.forEach(car -> carMap.put(car.getId(), car));
        return cars;
    }

    @Override
    public Optional<Car> getById(UUID id) {
        final var car = carMap.get(id);
        if (car == null) {
            return Optional.empty();
        }
        return Optional.of(car);
    }
}
