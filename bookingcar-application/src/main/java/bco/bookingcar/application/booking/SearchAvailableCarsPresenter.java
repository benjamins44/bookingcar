package bco.bookingcar.application.booking;

import bco.bookingcar.application.customer.GetCustomerResponse;

public interface SearchAvailableCarsPresenter<T> {
    void present(SearchAvailableCarsResponse response);

    T viewModel();
}
