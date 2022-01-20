package bco.bookingcar.infrastructure.primary.configuration;

import bco.bookingcar.annotation.ApplicationService;
import bco.bookingcar.application.BookingCarManager;
import bco.bookingcar.application.CarManager;
import bco.bookingcar.application.CustomerManager;
import bco.bookingcar.application.PlanningCarManager;
import bco.bookingcar.infrastructure.primary.fakes.BookingCarManagerFake;
import bco.bookingcar.infrastructure.primary.fakes.CarManagerFake;
import bco.bookingcar.infrastructure.primary.fakes.CustomerManagerFake;
import bco.bookingcar.infrastructure.primary.fakes.PlanningCarManagerFake;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@TestConfiguration
@ComponentScan(
        includeFilters =
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {ApplicationService.class})
)
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
