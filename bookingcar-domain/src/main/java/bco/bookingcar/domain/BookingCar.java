package bco.bookingcar.domain;

import bco.bookingcar.annotation.DomainService;
import bco.bookingcar.domain.booking.BookedCar;
import bco.bookingcar.domain.booking.CarNotAvailableException;
import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.customer.Customer;
import bco.bookingcar.domain.shared.Period;

@DomainService
public interface BookingCar {
    boolean carIsBookedOn(Car car, Period period);

    BookedCar book(Car car, Period period, Customer customer) throws CarNotAvailableException;
}
