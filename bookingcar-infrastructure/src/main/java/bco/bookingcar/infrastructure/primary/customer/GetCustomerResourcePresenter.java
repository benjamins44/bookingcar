package bco.bookingcar.infrastructure.primary.customer;

import bco.bookingcar.application.customer.GetCustomerPresenter;

public interface GetCustomerResourcePresenter extends GetCustomerPresenter {
    CustomerResource viewModel();
}
