package bco.bookingcar.infrastructure.secondary.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import bco.bookingcar.infrastructure.secondary.entities.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {
}
