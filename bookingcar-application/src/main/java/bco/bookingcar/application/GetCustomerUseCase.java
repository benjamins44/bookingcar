package bco.bookingcar.application;

import bco.bookingcar.application.customer.CustomerNotFoundException;
import bco.bookingcar.application.customer.GetCustomerPresenter;
import bco.bookingcar.application.customer.GetCustomerRequest;

public interface GetCustomerUseCase {
    void execute(GetCustomerRequest request, GetCustomerPresenter presenter) throws CustomerNotFoundException;
}
