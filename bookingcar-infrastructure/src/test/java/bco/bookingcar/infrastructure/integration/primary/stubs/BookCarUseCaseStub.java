package bco.bookingcar.infrastructure.integration.primary.stubs;

import bco.bookingcar.annotation.Stub;
import bco.bookingcar.application.BookCarUseCase;
import bco.bookingcar.application.booking.AvailableCar;
import bco.bookingcar.application.booking.BookCarPresenter;
import bco.bookingcar.application.booking.BookCarRequest;
import bco.bookingcar.application.booking.BookCarResponse;
import bco.bookingcar.application.booking.SearchAvailableCarsRequest;
import bco.bookingcar.domain.booking.BookedCar;
import bco.bookingcar.domain.shared.Period;
import bco.bookingcar.exceptions.BusinessException;
import bco.bookingcar.exceptions.TechnicalException;

import java.util.List;
import java.util.UUID;

@Stub
public class BookCarUseCaseStub implements BookCarUseCase {

    private BookedCar bookedCar;

    private BusinessException exception;

    public void reset() {
        this.bookedCar = null;
        this.exception = null;
    }

    public void setBookedCar(BookedCar bookedCar) {
        this.bookedCar = bookedCar;
    }

    public void setException(BusinessException exception) {
        this.exception = exception;
    }

    @Override
    public void execute(BookCarRequest request, BookCarPresenter presenter) throws BusinessException, TechnicalException {
        if (exception != null) throw exception;
        presenter.present(BookCarResponse.builder().bookedCar(bookedCar).build());
    }
}
