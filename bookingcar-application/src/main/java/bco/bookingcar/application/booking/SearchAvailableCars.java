package bco.bookingcar.application.booking;

import java.util.List;

public interface SearchAvailableCars {
    List<AvailableCar> searchAvailableCars(SearchAvailableCarsRequest request);
}
