package bco.bookingcar.domain;

import bco.bookingcar.annotation.DomainService;
import bco.bookingcar.domain.booking.BookedCar;
import bco.bookingcar.domain.booking.BookingCarAttempt;
import bco.bookingcar.domain.booking.CarNotAvailableException;
import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.shared.Period;

@DomainService
public interface BookingCar {
    Boolean carIsBookedOn(Car car, Period period);

    BookedCar book(BookingCarAttempt bookingCarAttempt) throws CarNotAvailableException;
}
