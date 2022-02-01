package bco.bookingcar.infrastructure.integration.primary.fakes;

import bco.bookingcar.application.BookingCarManager;
import bco.bookingcar.application.booking.AvailableCar;
import bco.bookingcar.application.booking.SearchAvailableCarsCriterias;
import bco.bookingcar.domain.booking.BookedCar;
import bco.bookingcar.domain.shared.Period;
import bco.bookingcar.exceptions.BusinessException;

import java.util.List;
import java.util.UUID;

public class BookingCarManagerFake implements BookingCarManager {

    private List<AvailableCar> availableCars = List.of();

    private BookedCar bookedCar;

    private BusinessException exception;

    @Override
    public List<AvailableCar> search(SearchAvailableCarsCriterias criterias) {
        return availableCars;
    }

    @Override
    public BookedCar book(UUID carId, UUID customerId, Period period) throws BusinessException {
        if (exception != null) throw exception;
        return bookedCar;
    }

    public void reset() {
        this.availableCars = List.of();
        this.bookedCar = null;
        this.exception = null;
    }

    public void setAvailableCars(List<AvailableCar> availableCars) {
        this.availableCars = availableCars;
    }

    public void setBookedCar(BookedCar bookedCar) {
        this.bookedCar = bookedCar;
    }

    public void setException(BusinessException exception) {
        this.exception = exception;
    }

}
