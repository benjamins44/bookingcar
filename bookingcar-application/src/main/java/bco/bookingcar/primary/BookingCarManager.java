package bco.bookingcar.primary;

import bco.bookingcar.car.CarNotFoundException;
import bco.bookingcar.customer.CustomerNotFoundException;
import bco.bookingcar.domain.booking.AvailableCar;
import bco.bookingcar.domain.booking.BookedCar;
import bco.bookingcar.domain.booking.CarNotAvailableException;
import bco.bookingcar.domain.booking.SearchAvailableCarsCriterias;
import bco.bookingcar.domain.shared.Period;

import java.util.List;
import java.util.UUID;

public interface BookingCarManager {
    List<AvailableCar> search(SearchAvailableCarsCriterias criterias);

    BookedCar book(UUID carId, UUID customerId, Period period) throws CarNotAvailableException, CarNotFoundException, CustomerNotFoundException;
}
