package bco.bookingcar.application.unit.customer;

import bco.bookingcar.application.customer.GetCustomerUseCaseImpl;
import bco.bookingcar.application.customer.CustomerNotFoundException;
import bco.bookingcar.application.customer.GetCustomer;
import bco.bookingcar.domain.ports.StoreCustomers;
import bco.bookingcar.domain.unit.InjectDomainObjects;
import bco.bookingcar.domain.unit.customer.CustomerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@InjectDomainObjects
@DisplayName("Get Customer test")
public class GetCustomerTest {

    private StoreCustomers storeCustomers;
    private GetCustomer getCustomer;

    @BeforeEach
    void setup(StoreCustomers storeCustomers) {
        this.storeCustomers = storeCustomers;
        getCustomer = new GetCustomerUseCaseImpl(storeCustomers);
    }

    @Nested
    @DisplayName("Find by id")
    class FindByIdTest {
        @Test
        void find_by_id_must_return_customer() throws CustomerNotFoundException {
            var customer = storeCustomers.add(CustomerFactory.build());

            var customerFound = getCustomer.findById(customer.getId());

            assertThat(customerFound).isEqualTo(customer);
        }

        @Test
        void find_by_id_shoud_return_exception_if_customer_not_exist() {
            assertThatThrownBy(() -> {
                var customer = CustomerFactory.build().withId(UUID.randomUUID());
                var customerFound = getCustomer.findById(customer.getId());
            }).isInstanceOf(CustomerNotFoundException.class);
        }
    }
}
