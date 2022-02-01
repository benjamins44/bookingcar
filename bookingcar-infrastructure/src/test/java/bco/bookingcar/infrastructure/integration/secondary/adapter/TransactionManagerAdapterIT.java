package bco.bookingcar.infrastructure.integration.secondary.adapter;

import bco.bookingcar.domain.unit.customer.CustomerFactory;
import bco.bookingcar.exceptions.BusinessException;
import bco.bookingcar.exceptions.TechnicalException;
import bco.bookingcar.infrastructure.integration.secondary.configuration.PostgresqlContainerConfiguration;
import bco.bookingcar.infrastructure.secondary.adapter.TransactionManagerAdapter;
import bco.bookingcar.infrastructure.secondary.entities.CustomerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Import(PostgresqlContainerConfiguration.class)
public class TransactionManagerAdapterIT {
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private EntityManager entityManager;

    private TransactionManagerAdapter transactionManagerAdapter;

    @BeforeEach
    public void setUp() {
        transactionManagerAdapter = new TransactionManagerAdapter(transactionManager);
    }

    @Test
    void persistCustomer_WhenNotDuplicate_ThenShouldCommit() throws BusinessException {
        UUID idCustomer = transactionManagerAdapter.executeInTransaction(() -> {
            var customer = CustomerEntity.fromDomain(CustomerFactory.build());
            entityManager.persist(customer);

            return customer.getId();
        });

        assertThat(
                entityManager.createQuery(String.format("select c from CustomerEntity c where c.id = '%s'", idCustomer)).getResultList()
        ).isNotEmpty();
    }

    @Test
    void persistCustomer_WhenRefIsDuplicate_ThenShouldRollback() {
        var sameId = UUID.randomUUID();

        assertThatThrownBy(() -> transactionManagerAdapter.executeInTransaction(() -> {
            var customerOne = CustomerEntity.fromDomain(CustomerFactory.build().withId(sameId));
            var customerTwo = CustomerEntity.fromDomain(CustomerFactory.build().withId(sameId));

            entityManager.persist(customerOne); // ok
            entityManager.persist(customerTwo); // fails

            return sameId;
        })).isInstanceOf(TechnicalException.class);

        assertThat(
                entityManager.createQuery(String.format("select c from CustomerEntity c where c.id = '%s'", sameId)).getResultList()
        ).isEmpty();
    }
}
