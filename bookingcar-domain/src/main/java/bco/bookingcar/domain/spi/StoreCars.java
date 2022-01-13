package bco.bookingcar.domain.spi;

import bco.bookingcar.domain.voiture.Car;

public interface StoreCars {
    Car add(Car car);
}
