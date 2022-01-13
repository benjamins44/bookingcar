package bco.bookingcar.domain.spi.stubs;

import bco.bookingcar.annotation.Stub;
import bco.bookingcar.domain.spi.StoreCars;
import bco.bookingcar.domain.voiture.Car;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Stubs repository de clients
 */
@Stub // annotation purement informative
public class InMemoryStoreCars implements StoreCars {

    private final Map<UUID, Car> carMap = new HashMap<>();

    @Override
    public Car add(Car car) {
        carMap.put(car.getId(), car);
        return car;
    }
}
