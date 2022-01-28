package bco.bookingcar.application;

import bco.bookingcar.application.booking.AvailableCar;
import bco.bookingcar.application.booking.SearchAvailableCarsCriterias;
import bco.bookingcar.domain.booking.BookedCar;
import bco.bookingcar.domain.shared.Period;
import bco.bookingcar.exceptions.BusinessException;
import bco.bookingcar.exceptions.TechnicalException;

import java.util.List;
import java.util.UUID;

public interface BookingCarManager {
    List<AvailableCar> search(SearchAvailableCarsCriterias criterias);

    BookedCar book(UUID carId, UUID customerId, Period period) throws BusinessException, TechnicalException;
}
