package bco.bookingcar.domain;

import bco.bookingcar.annotation.DomainService;
import bco.bookingcar.domain.booking.*;

import java.util.List;

@DomainService
public interface BookingCar {
    List<AvailableCar> search(SearchAvailableCarsCriterias criterias);

    BookedCar book(BookingCarAttempt bookingCarAttempt) throws CarNotAvailableException;
}
