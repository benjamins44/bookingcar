package bco.bookingcar.infrastructure.secondary.repositories;

import bco.bookingcar.infrastructure.secondary.entities.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarRepository extends JpaRepository<CarEntity, UUID> {
}
