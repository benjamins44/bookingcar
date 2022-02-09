package bco.bookingcar.domain.booking;

import bco.bookingcar.annotation.DomainService;
import bco.bookingcar.domain.BookingCar;
import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.customer.Customer;
import bco.bookingcar.domain.ports.StoreBookedCars;
import bco.bookingcar.domain.ports.StoreCars;
import bco.bookingcar.domain.shared.Period;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@DomainService
public class BookingCarImpl implements BookingCar {

    private StoreCars storeCars;
    private StoreBookedCars storeBookedCars;

    @Override
    public BookedCar book(Car car, Period period, Customer customer) throws CarNotAvailableException {
        if (carIsBookedOn(car, period)) {
            throw new CarNotAvailableException(car.getId());
        }

        var newBookedCar = BookedCar.builder()
                .idCar(car.getId())
                .idCustomer(customer.getId())
                .period(period)
                .build();

        var bookedCar = storeBookedCars.add(newBookedCar);

        return bookedCar.addEvent(
                BookedCarCreatedEvent.builder()
                        .car(car)
                        .customer(customer)
                        .period(period)
                        .build());
    }

    @Override
    public boolean carIsBookedOn(Car car, Period period) {
        return !storeBookedCars.getBookedCarByCarAndPeriod(car, period).isEmpty();
    }
}
