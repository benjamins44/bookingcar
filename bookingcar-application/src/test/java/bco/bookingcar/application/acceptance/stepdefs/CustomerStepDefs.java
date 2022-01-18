package bco.bookingcar.application.acceptance.stepdefs;

import bco.bookingcar.domain.customer.Customer;
import bco.bookingcar.domain.ports.StoreCustomers;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerStepDefs implements En {
    public CustomerStepDefs(StoreCustomers storeCustomers, TestContext testContext) {
        Given("^customers exist:$", (DataTable dataTable) -> {
            List<Map<String, String>> dataMaps = dataTable.asMaps(String.class, String.class);
            dataMaps.forEach(dataMap -> {

                Customer customer = Customer.builder()
                        .id(UUID.fromString(dataMap.get("id")))
                        .firstname(dataMap.get("firstname"))
                        .lastname(dataMap.get("lastname"))
                        .build();
                storeCustomers.add(customer);
            });
        });
        Given("^I am authenticated as \"([^\"]*)\"$", (String idCustomer) -> {
            Optional<Customer> customer = storeCustomers.getById(UUID.fromString(idCustomer));
            assertThat(customer).isPresent();
            testContext.setCustomer(customer);
        });
    }
}
