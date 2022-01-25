package bco.bookingcar.infrastructure.integration.secondary.adapter;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import bco.bookingcar.domain.unit.customer.CustomerFactory;
import bco.bookingcar.infrastructure.integration.secondary.configuration.PostgresqlContainerConfiguration;
import bco.bookingcar.infrastructure.secondary.adapter.StoreCustomersAdapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@ActiveProfiles("primary-integration-test")
public class StoreCustomersAdapterIT {

    private final static UUID customerExisting = UUID.fromString("123e4567-e89b-12d3-a456-426614174001");

    @Autowired
    private StoreCustomersAdapter storeCustomersAdapter;

    @Nested
    @DisplayName("Test add customer persistence")
    class AddCustomerTest {
        @Test
        @DisplayName("Add customer persistence works")
        void add_customer_work() {
            var customer = storeCustomersAdapter.add(CustomerFactory.build());

            assertThat(customer.getId()).isNotNull();
        }
    }

    @Nested
    @SpringBootTest
    @Import(PostgresqlContainerConfiguration.class)
    @Sql(scripts = { "classpath:IT_datas.sql" }, executionPhase = BEFORE_TEST_METHOD)
    @DisplayName("Test get customer by id persistence")
    class GetByIdTest {
        @Test
        @DisplayName("Get by id customer existing works")
        void get_by_id_existing_work() {
            var customer = storeCustomersAdapter.getById(customerExisting);

            assertThat(customer).isPresent();
        }

        @Test
        @DisplayName("Get by id customer not existing works")
        void get_by_id_not_existing_work() {
            var customer = storeCustomersAdapter.getById(UUID.randomUUID());

            assertThat(customer).isNotPresent();
        }
    }
}
