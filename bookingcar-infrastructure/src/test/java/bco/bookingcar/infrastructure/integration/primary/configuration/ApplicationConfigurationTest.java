package bco.bookingcar.infrastructure.integration.primary.configuration;

import bco.bookingcar.application.BookingCarManager;
import bco.bookingcar.application.CarManager;
import bco.bookingcar.application.CustomerManager;
import bco.bookingcar.application.PlanningCarManager;
import bco.bookingcar.infrastructure.integration.primary.stubs.BookingCarManagerStub;
import bco.bookingcar.infrastructure.integration.primary.stubs.CarManagerStub;
import bco.bookingcar.infrastructure.integration.primary.stubs.CustomerManagerStub;
import bco.bookingcar.infrastructure.integration.primary.stubs.PlanningCarManagerStub;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@TestConfiguration
@ComponentScan("bco.bookingcar.infrastructure.primary.error.status")
public class ApplicationConfigurationTest {

    @Bean
    public CustomerManager customerManager() {
        return new CustomerManagerStub();
    }

    @Bean
    public CarManager carManager() {
        return new CarManagerStub();
    }

    @Bean
    public PlanningCarManager planningCarManager() {
        return new PlanningCarManagerStub();
    }

    @Bean
    public BookingCarManager bookingCarManager() {
        return new BookingCarManagerStub();
    }
}
