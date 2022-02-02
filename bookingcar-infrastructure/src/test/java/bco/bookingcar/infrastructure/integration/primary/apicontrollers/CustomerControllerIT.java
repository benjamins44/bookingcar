package bco.bookingcar.infrastructure.integration.primary.apicontrollers;

import bco.bookingcar.application.CustomerManager;
import bco.bookingcar.domain.customer.Customer;
import bco.bookingcar.domain.unit.customer.CustomerFactory;
import bco.bookingcar.infrastructure.integration.primary.configuration.ApplicationConfigurationTest;
import bco.bookingcar.infrastructure.integration.primary.stubs.CustomerManagerStub;
import bco.bookingcar.infrastructure.primary.apicontrollers.CustomerController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({ApplicationConfigurationTest.class})
@WebMvcTest(CustomerController.class)
public class CustomerControllerIT {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private CustomerManager customerManager;

    @BeforeEach
    void setup() {
        this.setCustomerToManager(null);
    }

    @Test
    void can_get_customer_by_id() throws Exception {
        var customer = CustomerFactory.build().withId(UUID.randomUUID());
        this.setCustomerToManager(customer);

        this.mvc.perform(
                        get(String.format("/customers/%s", customer.getId()))
                                .accept(APPLICATION_JSON)
                                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(customer.getId().toString())))
                .andExpect(jsonPath("$.firstname", is(customer.getFirstname())))
                .andExpect(jsonPath("$.lastname", is(customer.getLastname())));
    }

    @Test
    void receive_not_found_if_customer_not_found() throws Exception {
        var idCustomer = UUID.randomUUID();
        this.mvc.perform(
                        get(String.format("/customers/%s", idCustomer))
                                .accept(APPLICATION_JSON)
                                .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error.code", is(String.valueOf(HttpStatus.NOT_FOUND.value()))))
                .andExpect(jsonPath("$.error.message", is(String.format("The customer %s does not exist.", idCustomer))))
                .andExpect(jsonPath("$.error.errors[0].message", is("error.customer-not-found")));
    }

    private void setCustomerToManager(Customer customer) {
        ((CustomerManagerStub) customerManager).setCustomer(customer);
    }
}
