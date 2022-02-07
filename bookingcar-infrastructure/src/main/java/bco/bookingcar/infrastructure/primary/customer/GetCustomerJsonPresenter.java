package bco.bookingcar.infrastructure.primary.customer;

import bco.bookingcar.application.customer.GetCustomerResponse;
import org.springframework.stereotype.Service;

@Service
public class GetCustomerJsonPresenter implements GetCustomerResourcePresenter {

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
