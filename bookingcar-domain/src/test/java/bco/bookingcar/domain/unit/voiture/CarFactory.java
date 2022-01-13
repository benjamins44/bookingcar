package bco.bookingcar.domain.unit.voiture;

import bco.bookingcar.domain.voiture.Car;

import java.util.UUID;

public interface CarFactory {
    static Car build(UUID id) {
        return Car.builder()
                .id(id)
                .brand("DACIA")
                .model("Stepway")
                .numberOfPlace(5)
                .category(CarCategoryFactory.build(null))
                .build();
    }
}
