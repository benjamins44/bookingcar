package bco.bookingcar.application.unit.customer;

import bco.bookingcar.customer.BcoCustomerManager;
import bco.bookingcar.customer.CustomerNotFoundException;
import bco.bookingcar.domain.ports.StoreCustomers;
import bco.bookingcar.domain.unit.InjectDomainObjects;
import bco.bookingcar.domain.unit.customer.CustomerFactory;
import bco.bookingcar.primary.CustomerManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@InjectDomainObjects
@DisplayName("Customer manager test")
public class BcoCustomerManagerTest {

    private StoreCustomers storeCustomers;
    private CustomerManager customerManager;

    @BeforeEach
    void setup(StoreCustomers storeCustomers) {
        this.storeCustomers = storeCustomers;
        customerManager = new BcoCustomerManager(storeCustomers);
    }

    @Nested
    @DisplayName("Find by id")
    class FindByIdTest {
        @Test
        void find_by_id_must_return_customer() throws CustomerNotFoundException {
            var customer = storeCustomers.add(CustomerFactory.build());

            var customerFound = customerManager.findById(customer.getId());

            assertThat(customerFound).isEqualTo(customer);
        }

        @Test
        void find_by_id_shoud_return_exception_if_customer_not_exist() {
            assertThatThrownBy(() -> {
                var customer = CustomerFactory.build().withId(UUID.randomUUID());
                var customerFound = customerManager.findById(customer.getId());
            }).isInstanceOf(CustomerNotFoundException.class);
        }
    }
}
