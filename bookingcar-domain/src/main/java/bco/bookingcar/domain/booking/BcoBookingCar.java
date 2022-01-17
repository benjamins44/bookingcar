package bco.bookingcar.domain.booking;

import bco.bookingcar.annotation.DomainService;
import bco.bookingcar.domain.BookingCar;
import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.secondary.StoreBookedCar;
import bco.bookingcar.domain.secondary.StoreCars;
import bco.bookingcar.domain.shared.Period;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@DomainService
public class BcoBookingCar implements BookingCar {

    private StoreCars storeCars;
    private StoreBookedCar storeBookedCar;

    @Override
    public BookedCar book(BookingCarAttempt bookingCarAttempt) throws CarNotAvailableException {
        if (carIsBookedOn(bookingCarAttempt.getCar(), bookingCarAttempt.getPeriod())) {
            throw new CarNotAvailableException();
        }
        var newBookedCar = BookedCar.builder()
                .idCar(bookingCarAttempt.getCar().getId())
                .idCustomer(bookingCarAttempt.getCustomer().getId())
                .period(bookingCarAttempt.getPeriod())
                .build();
        return storeBookedCar.add(newBookedCar);
    }

    @Override
    public Boolean carIsBookedOn(Car car, Period period) {
        return !storeBookedCar.getBookedCarByCarAndPeriod(car, period).isEmpty();
    }
}
