package bco.bookingcar.application.unit.customer;

import bco.bookingcar.application.GetCustomerUseCase;
import bco.bookingcar.application.customer.*;
import bco.bookingcar.domain.customer.Customer;
import bco.bookingcar.domain.ports.StoreCustomers;
import bco.bookingcar.domain.unit.InjectDomainObjects;
import bco.bookingcar.domain.unit.customer.CustomerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@InjectDomainObjects
@DisplayName("Get Customer Use Case test")
public class GetCustomerUseCaseTest implements GetCustomerPresenter<Customer> {

    private StoreCustomers storeCustomers;
    private GetCustomerResponse getCustomerResponse;
    private GetCustomerUseCase getCustomerUseCase;

    @BeforeEach
    void setup(StoreCustomers storeCustomers) {
        this.storeCustomers = storeCustomers;
        this.getCustomerUseCase = new BcoGetCustomer(storeCustomers);
    }

    @Override
    public void present(GetCustomerResponse response) {
        this.getCustomerResponse = response;
    }

    @Override
    public Customer viewModel() {
        return this.getCustomerResponse.getCustomer();
    }

    @DisplayName("customer is in the response")
    @Test
    void customer_is_in_response() throws CustomerNotFoundException {
        var customer = storeCustomers.add(CustomerFactory.build());

        getCustomerUseCase.execute(GetCustomerRequest.builder().idCustomer(customer.getId()).build(), this);

        assertThat(getCustomerResponse.getCustomer()).isEqualTo(customer);
    }
}
