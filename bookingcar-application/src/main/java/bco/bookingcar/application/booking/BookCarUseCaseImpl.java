package bco.bookingcar.application.booking;

import bco.bookingcar.annotation.ApplicationService;
import bco.bookingcar.application.BookCarUseCase;
import bco.bookingcar.application.car.GetCar;
import bco.bookingcar.application.customer.GetCustomer;
import bco.bookingcar.domain.BookingCar;
import bco.bookingcar.domain.booking.BookedCar;
import bco.bookingcar.domain.ports.BookingCarEventsDispatcher;
import bco.bookingcar.exceptions.BusinessException;
import bco.bookingcar.exceptions.TechnicalException;
import bco.bookingcar.ports.TransactionManager;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@ApplicationService
public class BookCarUseCaseImpl implements BookCarUseCase, BookCar {

    private BookingCar bookingCar;
    private GetCar getCar;
    private GetCustomer getCustomer;
    private BookingCarEventsDispatcher bookingCarEventsDispatcher;
    private TransactionManager transactionManager;

    @Override
    public void execute(BookCarRequest request, BookCarPresenter presenter) throws BusinessException, TechnicalException {
        presenter.present(BookCarResponse.builder().bookedCar(book(request)).build());
    }

    @Override
    public BookedCar book(BookCarRequest request) throws BusinessException, TechnicalException {
        var car = getCar.findById(request.getCarId());
        var customer = getCustomer.findById(request.getCustomerId());
        var bookedCar = transactionManager.executeInTransaction(() -> bookingCar.book(car, request.getPeriod(), customer));
        bookingCarEventsDispatcher.dispatch(bookedCar.getCreatedEvents());

        return bookedCar;
    }
}

