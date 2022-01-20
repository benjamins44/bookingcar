package bco.bookingcar.infrastructure.primary.apicontrollers;

import bco.bookingcar.application.CustomerManager;
import bco.bookingcar.domain.customer.Customer;
import bco.bookingcar.domain.unit.customer.CustomerFactory;
import bco.bookingcar.infrastructure.ReservationVoituresApplication;
import bco.bookingcar.infrastructure.primary.configuration.ApplicationConfigurationTest;
import bco.bookingcar.infrastructure.primary.fakes.CustomerManagerFake;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import({
        ReservationVoituresApplication.class,
        ApplicationConfigurationTest.class
})
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
    void receive_bad_request_if_customer_not_found() throws Exception {
        this.mvc.perform(
                        get(String.format("/customers/%s", UUID.randomUUID()))
                                .accept(APPLICATION_JSON)
                                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    private void setCustomerToManager(Customer customer) {
        ((CustomerManagerFake) customerManager).setCustomer(customer);
    }
}
