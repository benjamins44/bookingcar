package bco.bookingcar.application.booking;

import bco.bookingcar.annotation.ApplicationService;
import bco.bookingcar.application.BookingCarManager;
import bco.bookingcar.application.CarManager;
import bco.bookingcar.application.CustomerManager;
import bco.bookingcar.domain.BookingCar;
import bco.bookingcar.domain.booking.BookedCar;
import bco.bookingcar.domain.ports.BookingCarEventsDispatcher;
import bco.bookingcar.domain.ports.StoreCars;
import bco.bookingcar.domain.shared.Period;
import bco.bookingcar.exceptions.BusinessException;
import bco.bookingcar.exceptions.TechnicalException;
import bco.bookingcar.ports.TransactionManager;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@ApplicationService
public class BcoBookingCarManager implements BookingCarManager {

    private BookingCar bookingCar;
    private StoreCars storeCars;
    private CarManager carManager;
    private CustomerManager customerManager;
    private BookingCarEventsDispatcher bookingCarEventsDispatcher;
    private TransactionManager transactionManager;

    @Override
    public List<AvailableCar> search(SearchAvailableCarsCriterias criterias) {
        return storeCars
                .getAll()
                .stream()
                .filter(car -> !bookingCar.carIsBookedOn(car, criterias.getPeriod()))
                .map(car -> AvailableCar.builder()
                        .car(car)
                        .period(criterias.getPeriod())
                        .build()
                ).collect(Collectors.toList());
    }

    @Override
    public BookedCar book(UUID carId, UUID customerId, Period period) throws BusinessException, TechnicalException {
        var car = carManager.findById(carId);
        var customer = customerManager.findById(customerId);
        var bookedCar = transactionManager.executeInTransaction(() ->
                bookingCar.book(car, period, customer)
        );
        bookingCarEventsDispatcher.dispatch(bookedCar.getCreatedEvents());
        return bookedCar;
    }
}

