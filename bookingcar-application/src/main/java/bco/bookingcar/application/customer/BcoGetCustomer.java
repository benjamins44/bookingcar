package bco.bookingcar.application.customer;

import bco.bookingcar.annotation.ApplicationService;
import bco.bookingcar.application.GetCustomerUseCase;
import bco.bookingcar.domain.customer.Customer;
import bco.bookingcar.domain.ports.StoreCustomers;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@ApplicationService
public class BcoGetCustomer implements GetCustomerUseCase, GetCustomer {
    private StoreCustomers storeCustomers;

    @Override
    public void execute(GetCustomerRequest request, GetCustomerPresenter presenter) throws CustomerNotFoundException {
        var customerId = request.getIdCustomer();
        var customer = this.findById(customerId);
        presenter.present(GetCustomerResponse.builder().customer(customer).build());
    }

    @Override
    public Customer findById(UUID customerId) throws CustomerNotFoundException {
        return storeCustomers.getById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
    }
}
