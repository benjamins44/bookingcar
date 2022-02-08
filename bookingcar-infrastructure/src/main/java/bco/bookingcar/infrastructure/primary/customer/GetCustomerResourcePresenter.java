package bco.bookingcar.infrastructure.primary.customer;

import org.springframework.stereotype.Component;

import bco.bookingcar.application.customer.GetCustomerPresenter;
import bco.bookingcar.application.customer.GetCustomerResponse;

@Component
public class GetCustomerResourcePresenter implements GetCustomerPresenter<CustomerResource> {

    private CustomerResource viewModel;

    @Override
    public void present(GetCustomerResponse response) {
        this.viewModel = CustomerResource.fromDomain(response.getCustomer());
    }

    @Override
    public CustomerResource viewModel() {
        return viewModel;
    }
}
