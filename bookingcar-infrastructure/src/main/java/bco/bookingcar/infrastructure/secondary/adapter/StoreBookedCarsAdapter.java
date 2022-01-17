package bco.bookingcar.infrastructure.secondary.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import bco.bookingcar.domain.booking.BookedCar;
import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.ports.StoreBookedCars;
import bco.bookingcar.domain.shared.Period;
import bco.bookingcar.infrastructure.secondary.entities.BookedCarEntity;
import bco.bookingcar.infrastructure.secondary.repositories.BookedCarRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class StoreBookedCarsAdapter implements StoreBookedCars {

    private BookedCarRepository bookedCarRepository;

    @Override
    public BookedCar add(BookedCar bookedCar) {
        return bookedCarRepository.save(BookedCarEntity.fromDomain(bookedCar)).toDomain();
    }

    @Override
    public List<BookedCar> getAll(Period period) {
        return bookedCarRepository.findBookedCarEntityByStartDateTimeIsLessThanEqualAndEndDateTimeGreaterThanEqual(
                period.getEndDateTime(),
                period.getStartDateTime()
        ).stream().map(BookedCarEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<BookedCar> getBookedCarByCarAndPeriod(Car car, Period period) {
        return bookedCarRepository.findBookedCarEntityByStartDateTimeIsLessThanEqualAndEndDateTimeGreaterThanEqualAndIdCarIs(
                period.getEndDateTime(),
                period.getStartDateTime(),
                car.getId()
        ).stream().map(BookedCarEntity::toDomain).collect(Collectors.toList());
    }
}
