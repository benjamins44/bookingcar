package bco.bookingcar.infrastructure.secondary.repositories;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import bco.bookingcar.infrastructure.secondary.entities.BookedCarEntity;

public interface BookedCarRepository extends JpaRepository<BookedCarEntity, UUID> {
    List<BookedCarEntity> findBookedCarEntityByStartDateTimeIsLessThanEqualAndEndDateTimeGreaterThanEqual(ZonedDateTime startDateTime, ZonedDateTime endtDateTime);

    List<BookedCarEntity> findBookedCarEntityByStartDateTimeIsLessThanEqualAndEndDateTimeGreaterThanEqualAndIdCarIs(ZonedDateTime startDateTime, ZonedDateTime endtDateTime, UUID carId);
}
