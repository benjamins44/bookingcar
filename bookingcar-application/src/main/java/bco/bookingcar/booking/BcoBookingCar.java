package bco.bookingcar.booking;

import bco.bookingcar.annotation.ApplicationService;
import bco.bookingcar.car.CarNotFoundException;
import bco.bookingcar.customer.CustomerNotFoundException;
import bco.bookingcar.domain.SearchAvailableCars;
import bco.bookingcar.domain.booking.*;
import bco.bookingcar.domain.ports.StoreBookedCar;
import bco.bookingcar.domain.shared.Period;
import bco.bookingcar.primary.BookingCar;
import bco.bookingcar.primary.CarManager;
import bco.bookingcar.primary.CustomerManager;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@ApplicationService
public class BcoBookingCar implements BookingCar {

    private SearchAvailableCars searchAvailableCars;
    private StoreBookedCar storeBookedCar;
    private CarManager carManager;
    private CustomerManager customerManager;

    @Override
    public List<AvailableCar> search(SearchAvailableCarsCriterias criterias) {
        return searchAvailableCars.search(criterias);
    }

    @Override
    public BookedCar book(UUID carId, UUID customerId, Period period) throws CarNotAvailableException, CarNotFoundException, CustomerNotFoundException {
        var car = carManager.findById(carId);
        var customer = customerManager.findById(customerId);
        var bookingCarAttempt = BookingCarAttempt.builder()
                .period(period)
                .car(car)
                .customer(customer)
                .build();
        return bookingCarAttempt.book(storeBookedCar);
    }
}

