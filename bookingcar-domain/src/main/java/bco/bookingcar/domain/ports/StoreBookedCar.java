package bco.bookingcar.domain.ports;

import bco.bookingcar.domain.booking.BookedCar;
import bco.bookingcar.domain.shared.Period;

import java.util.List;

public interface StoreBookedCar {
    BookedCar add(BookedCar build);

    List<BookedCar> getAll(Period period);
}
