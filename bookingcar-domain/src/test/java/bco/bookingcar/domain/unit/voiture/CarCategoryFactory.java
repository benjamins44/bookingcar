package bco.bookingcar.domain.unit.voiture;

import bco.bookingcar.domain.voiture.CarCategory;

import java.util.UUID;

public interface CarCategoryFactory {
    static CarCategory build(UUID id) {
        return CarCategory.builder()
                .id(id)
                .label("Citadine")
                .build();
    }
}
