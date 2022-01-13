package bco.bookingcar.domain.spi;

import bco.bookingcar.annotation.DomainRepository;
import bco.bookingcar.domain.voiture.CarCategory;

import java.util.Optional;
import java.util.UUID;

@DomainRepository
public interface StoreCarCategory {
    CarCategory add(CarCategory category);

    Optional<CarCategory> getById(UUID category);
}
