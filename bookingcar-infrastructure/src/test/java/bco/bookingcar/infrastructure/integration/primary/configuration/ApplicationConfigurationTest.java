package bco.bookingcar.infrastructure.integration.primary.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ControllerAdvice;

import bco.bookingcar.application.BookingCarManager;
import bco.bookingcar.application.GetCarUseCase;
import bco.bookingcar.application.GetCustomerUseCase;
import bco.bookingcar.application.PlanningCarManager;
import bco.bookingcar.infrastructure.integration.primary.stubs.BookingCarManagerStub;
import bco.bookingcar.infrastructure.integration.primary.stubs.CarManagerStub;
import bco.bookingcar.infrastructure.integration.primary.stubs.GetCustomerUseCaseStub;
import bco.bookingcar.infrastructure.integration.primary.stubs.PlanningCarManagerStub;

@TestConfiguration
@ComponentScan(basePackages = "bco.bookingcar.infrastructure.primary", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class),
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = ControllerAdvice.class),
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Repository.class) })
public class ApplicationConfigurationTest {

    @Bean
    public GetCustomerUseCase customerManager() {
        return new GetCustomerUseCaseStub();
    }

    @Bean
    public GetCarUseCase carManager() {
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
