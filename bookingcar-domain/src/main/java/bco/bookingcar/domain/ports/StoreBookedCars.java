package bco.bookingcar.domain.ports;

import bco.bookingcar.annotation.DomainRepository;
import bco.bookingcar.domain.booking.BookedCar;
import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.shared.Period;

import java.util.List;

@DomainRepository
public interface StoreBookedCars {
    BookedCar add(BookedCar build);

    List<BookedCar> getAll(Period period);

    List<BookedCar> getBookedCarByCarAndPeriod(Car car, Period period);
}
