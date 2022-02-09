package bco.bookingcar.application.unit.customer;

import bco.bookingcar.application.customer.GetCustomerRequest;
import bco.bookingcar.exceptions.BusinessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("GetCustomerRequest invariant tests")
class GetCustomerRequestTest {
    @Test
    @DisplayName("customerId must not be null")
    void id_car_must_not_be_null() {
        assertThatThrownBy(() -> GetCustomerRequest.builder().idCustomer(null).build()).isInstanceOf(BusinessException.class);
    }
}