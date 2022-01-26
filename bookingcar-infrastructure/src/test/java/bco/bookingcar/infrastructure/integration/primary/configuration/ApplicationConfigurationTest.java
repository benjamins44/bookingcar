package bco.bookingcar.infrastructure.integration.primary.configuration;

import bco.bookingcar.application.BookingCarManager;
import bco.bookingcar.application.CarManager;
import bco.bookingcar.application.CustomerManager;
import bco.bookingcar.application.PlanningCarManager;
import bco.bookingcar.infrastructure.integration.primary.fakes.BookingCarManagerFake;
import bco.bookingcar.infrastructure.integration.primary.fakes.CarManagerFake;
import bco.bookingcar.infrastructure.integration.primary.fakes.CustomerManagerFake;
import bco.bookingcar.infrastructure.integration.primary.fakes.PlanningCarManagerFake;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ApplicationConfigurationTest {

    @Bean
    public CustomerManager customerManager() {
        return new CustomerManagerFake();
    }

    @Bean
    public CarManager carManager() {
        return new CarManagerFake();
    }

    @Bean
    public PlanningCarManager planningCarManager() {
        return new PlanningCarManagerFake();
    }

    @Bean
    public BookingCarManager bookingCarManager() {
        return new BookingCarManagerFake();
    }
}
