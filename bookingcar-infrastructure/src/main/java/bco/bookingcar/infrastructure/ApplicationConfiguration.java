package bco.bookingcar.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import bco.bookingcar.application.BookCarUseCase;
import bco.bookingcar.application.GetCarUseCase;
import bco.bookingcar.application.GetCustomerUseCase;
import bco.bookingcar.application.GetPlanningCarUseCase;
import bco.bookingcar.application.SearchAvailableCarsUseCase;
import bco.bookingcar.application.booking.BookCarUseCaseImpl;
import bco.bookingcar.application.booking.SearchAvailableCarsUseCaseImpl;
import bco.bookingcar.application.car.GetCarUseCaseImpl;
import bco.bookingcar.application.car.GetCar;
import bco.bookingcar.application.customer.GetCustomerUseCaseImpl;
import bco.bookingcar.application.customer.GetCustomer;
import bco.bookingcar.application.planning.GetPlanningCarUseCaseImpl;
import bco.bookingcar.domain.booking.BookingCarImpl;
import bco.bookingcar.domain.ports.BookingCarEventsDispatcher;
import bco.bookingcar.domain.ports.StoreBookedCars;
import bco.bookingcar.domain.ports.StoreCars;
import bco.bookingcar.domain.ports.StoreCustomers;
import bco.bookingcar.ports.TransactionManager;

@Configuration
public class ApplicationConfiguration {

    @Autowired
    private StoreCars storeCars;

    @Autowired
    private StoreCustomers storeCustomers;

    @Autowired
    private StoreBookedCars storeBookedCars;

    @Bean
    public GetCustomerUseCase getCustomerUseCase() {
        return new GetCustomerUseCaseImpl(storeCustomers);
    }

    @Bean
    public GetCustomer getCustomer() {
        return new GetCustomerUseCaseImpl(storeCustomers);
    }

    @Bean
    public GetCarUseCase getCarUseCase() {
        return new GetCarUseCaseImpl(storeCars);
    }

    @Bean
    public GetCar getCar() {
        return new GetCarUseCaseImpl(storeCars);
    }

    @Bean
    public GetPlanningCarUseCase getPlanningCarUseCase() {
        return new GetPlanningCarUseCaseImpl(storeCars, storeBookedCars, storeCustomers);
    }

    @Bean
    public BookCarUseCase bookCarUseCase(TransactionManager transactionManager, BookingCarEventsDispatcher bookingCarEventsDispatcher) {
        return new BookCarUseCaseImpl(new BookingCarImpl(storeCars, storeBookedCars), getCar(), getCustomer(), bookingCarEventsDispatcher,
                transactionManager);
    }

    @Bean
    public SearchAvailableCarsUseCase searchAvailableCarsUseCase() {
        return new SearchAvailableCarsUseCaseImpl(new BookingCarImpl(storeCars, storeBookedCars), storeCars);
    }

}
