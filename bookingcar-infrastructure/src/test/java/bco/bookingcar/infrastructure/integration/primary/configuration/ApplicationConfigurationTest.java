package bco.bookingcar.infrastructure.integration.primary.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ControllerAdvice;

import bco.bookingcar.application.BookCarUseCase;
import bco.bookingcar.application.GetCarUseCase;
import bco.bookingcar.application.GetCustomerUseCase;
import bco.bookingcar.application.GetPlanningCarUseCase;
import bco.bookingcar.application.SearchAvailableCarsUseCase;
import bco.bookingcar.infrastructure.integration.primary.stubs.BookCarUseCaseStub;
import bco.bookingcar.infrastructure.integration.primary.stubs.CarManagerStub;
import bco.bookingcar.infrastructure.integration.primary.stubs.GetCustomerUseCaseStub;
import bco.bookingcar.infrastructure.integration.primary.stubs.GetPlanningCarUseCaseStub;
import bco.bookingcar.infrastructure.integration.primary.stubs.SearchAvailableCarsUseCaseStub;

@TestConfiguration
@ComponentScan(basePackages = "bco.bookingcar.infrastructure.primary", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class),
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = ControllerAdvice.class),
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Repository.class) })
public class ApplicationConfigurationTest {

    @Bean
    public GetCustomerUseCase getCustomerUseCase() {
        return new GetCustomerUseCaseStub();
    }

    @Bean
    public GetCarUseCase getCarUseCase() {
        return new CarManagerStub();
    }

    @Bean
    public GetPlanningCarUseCase getPlanningCarUseCase() {
        return new GetPlanningCarUseCaseStub();
    }

    @Bean
    public BookCarUseCase bookCarUseCase() {
        return new BookCarUseCaseStub();
    }

    @Bean
    public SearchAvailableCarsUseCase searchAvailableCarsUseCase() {
        return new SearchAvailableCarsUseCaseStub();
    }
}
