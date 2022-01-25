package bco.bookingcar.infrastructure.endtoend;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import com.intuit.karate.junit5.Karate;

import bco.bookingcar.infrastructure.ReservationVoituresApplication;
import bco.bookingcar.infrastructure.integration.secondary.configuration.PostgresqlContainerConfiguration;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@Import({ ReservationVoituresApplication.class, PostgresqlContainerConfiguration.class })
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = { "classpath:IT_datas.sql" }, executionPhase = BEFORE_TEST_METHOD)
public class AllTestRunnerE2E implements InitializingBean {
    @LocalServerPort
    int port;

    @Karate.Test
    Karate testAll() {
        return Karate.run().relativeTo(getClass());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.setProperty("local.server.port", String.valueOf(port));
    }
}
