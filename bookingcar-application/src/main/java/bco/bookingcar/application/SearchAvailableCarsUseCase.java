package bco.bookingcar.application;

import bco.bookingcar.application.booking.SearchAvailableCarsPresenter;
import bco.bookingcar.application.booking.SearchAvailableCarsRequest;

public interface SearchAvailableCarsUseCase {
    void execute(SearchAvailableCarsRequest request, SearchAvailableCarsPresenter presenter);
}
