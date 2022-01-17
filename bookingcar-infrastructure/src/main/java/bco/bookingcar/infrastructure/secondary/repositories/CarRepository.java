package bco.bookingcar.infrastructure.secondary.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import bco.bookingcar.infrastructure.secondary.entities.CarEntity;

public interface CarRepository extends JpaRepository<CarEntity, UUID> {
}
