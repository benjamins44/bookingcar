package bco.bookingcar.domain.spi.stubs;

import bco.bookingcar.annotation.Stub;
import bco.bookingcar.domain.spi.StoreCarCategory;
import bco.bookingcar.domain.voiture.CarCategory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Stubs repository de clients
 */
@Stub // annotation purement informative
public class InMemoryStoreCarCategory implements StoreCarCategory {

    private final Map<UUID, CarCategory> carCategoryMap = new HashMap<>();

    @Override
    public CarCategory add(CarCategory category) {
        carCategoryMap.put(category.getId(), category);
        return category;
    }

    @Override
    public Optional<CarCategory> getById(UUID category) {
        if (carCategoryMap.get(category) != null) {
            return Optional.of(carCategoryMap.get(category));
        }
        return Optional.empty();
    }
}
