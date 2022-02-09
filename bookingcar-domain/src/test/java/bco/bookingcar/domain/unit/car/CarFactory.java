package bco.bookingcar.domain.unit.car;

import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.car.CarCategory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface CarFactory {
    static Car build() {
        return Car.builder()
                .brand("DACIA")
                .model("Stepway")
                .numberOfPlace(5)
                .category(CarCategory.SEDAN)
                .build();
    }

    static List<Car> buildCars(int nb) {
        return IntStream.range(0, nb).mapToObj(i -> build()).toList();
    }
}
