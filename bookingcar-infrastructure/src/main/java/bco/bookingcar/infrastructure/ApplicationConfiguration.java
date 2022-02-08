package bco.bookingcar.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import bco.bookingcar.application.BookingCarManager;
import bco.bookingcar.application.GetCarUseCase;
import bco.bookingcar.application.GetCustomerUseCase;
import bco.bookingcar.application.PlanningCarManager;
import bco.bookingcar.application.booking.BcoBookingCarManager;
import bco.bookingcar.application.car.BcoGetCar;
import bco.bookingcar.application.car.GetCar;
import bco.bookingcar.application.customer.BcoGetCustomer;
import bco.bookingcar.application.customer.GetCustomer;
import bco.bookingcar.application.planning.BcoPlanningCarManager;
import bco.bookingcar.domain.booking.BcoBookingCar;
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
        return new BcoGetCustomer(storeCustomers);
    }

    @Bean
    public GetCustomer getCustomer() {
        return new BcoGetCustomer(storeCustomers);
    }

    @Bean
    public GetCarUseCase getCarUseCase() {
        return new BcoGetCar(storeCars);
    }

    @Bean
    public GetCar getCar() {
        return new BcoGetCar(storeCars);
    }

    @Bean
    public PlanningCarManager planningCarManager() {
        return new BcoPlanningCarManager(storeCars, storeBookedCars, storeCustomers);
    }

    @Bean
    public BookingCarManager bookingCarManager(TransactionManager transactionManager, BookingCarEventsDispatcher bookingCarEventsDispatcher) {
        return new BcoBookingCarManager(new BcoBookingCar(storeCars, storeBookedCars), storeCars, getCar(), getCustomer(), bookingCarEventsDispatcher,
                transactionManager);
    }
}
