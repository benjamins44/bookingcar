package bco.bookingcar.application.booking;

import bco.bookingcar.domain.booking.BookedCar;
import bco.bookingcar.exceptions.BusinessException;
import bco.bookingcar.exceptions.TechnicalException;

public interface BookCar {
    BookedCar book(BookCarRequest request) throws BusinessException, TechnicalException;
}
