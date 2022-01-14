package bco.bookingcar.domain.car;

import bco.bookingcar.annotation.ValueObject;

import java.util.Arrays;

@ValueObject
public enum CarCategory {
    SEDAN("Sedan"),
    CITY_DWELLER("CityDweller"),
    MINIVAN("Minivan");

    private final String value;

    CarCategory(String value) {
        this.value = value;
    }

    public static CarCategory getByValue(String value) {
        return Arrays.stream(CarCategory.values()).filter(carCategory -> carCategory.value.equals(value)).findFirst().orElse(SEDAN);
    }
}
