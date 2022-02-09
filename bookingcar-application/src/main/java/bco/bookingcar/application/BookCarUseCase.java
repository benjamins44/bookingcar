package bco.bookingcar.application;

import bco.bookingcar.application.booking.BookCarPresenter;
import bco.bookingcar.application.booking.BookCarRequest;
import bco.bookingcar.exceptions.BusinessException;
import bco.bookingcar.exceptions.TechnicalException;

public interface BookCarUseCase {
    void execute(BookCarRequest request, BookCarPresenter presenter) throws BusinessException, TechnicalException;
}
