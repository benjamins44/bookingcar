package bco.bookingcar.infrastructure.integration.primary.stubs;

import bco.bookingcar.annotation.Stub;
import bco.bookingcar.application.GetCustomerUseCase;
import bco.bookingcar.application.customer.CustomerNotFoundException;
import bco.bookingcar.application.customer.GetCustomerPresenter;
import bco.bookingcar.application.customer.GetCustomerRequest;
import bco.bookingcar.application.customer.GetCustomerResponse;
import bco.bookingcar.domain.customer.Customer;

import java.util.Optional;

@Stub
public class GetCustomerUseCaseStub implements GetCustomerUseCase {
    private Optional<Customer> customer = Optional.empty();

    public void setCustomer(Customer customer) {
        this.customer = customer == null ? Optional.empty() : Optional.of(customer);
    }

    @Override
    public void execute(GetCustomerRequest request, GetCustomerPresenter presenter) throws CustomerNotFoundException {
        presenter.present(
                GetCustomerResponse.builder()
                        .customer(customer.orElseThrow(() -> new CustomerNotFoundException(request.getIdCustomer())))
                        .build()
        );
    }
}
