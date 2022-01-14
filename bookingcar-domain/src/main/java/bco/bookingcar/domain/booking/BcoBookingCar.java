package bco.bookingcar.domain.booking;

import bco.bookingcar.annotation.DomainService;
import bco.bookingcar.domain.BookingCar;
import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.ports.StoreBookedCar;
import bco.bookingcar.domain.ports.StoreCars;
import bco.bookingcar.domain.shared.Period;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@DomainService
public class BcoBookingCar implements BookingCar {

    private StoreCars storeCars;
    private StoreBookedCar storeBookedCar;

    @Override
    public List<AvailableCar> search(SearchAvailableCarsCriterias criterias) {
        return storeCars
                .getAll()
                .stream()
                .filter(car -> carIsNotBookedIn(car, criterias.getPeriod()))
                .map(car -> AvailableCar.builder()
                        .idCar(car.getId())
                        .period(criterias.getPeriod())
                        .build()
                ).collect(Collectors.toList());
    }

    @Override
    public BookedCar book(BookingCarAttempt bookingCarAttempt) throws CarNotAvailableException {
        if (carIsBookedOn(bookingCarAttempt.getCar(), bookingCarAttempt.getPeriod())) {
            throw new CarNotAvailableException();
        }
        return storeBookedCar.add(BookedCar.builder()
                .idCar(bookingCarAttempt.getCar().getId())
                .idCustomer(bookingCarAttempt.getCustomer().getId())
                .period(bookingCarAttempt.getPeriod())
                .build());
    }

    private boolean carIsNotBookedIn(Car car, Period period) {
        var bookedCars = storeBookedCar.getAll(period);
        return bookedCars.stream()
                .map(BookedCar::getIdCar)
                .noneMatch(id -> id.equals(car.getId()));
    }

    private boolean carIsBookedOn(Car car, Period period) {
        return !this.carIsNotBookedIn(car, period);
    }
}
