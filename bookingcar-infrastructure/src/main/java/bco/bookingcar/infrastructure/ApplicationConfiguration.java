package bco.bookingcar.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import bco.bookingcar.application.BookingCarManager;
import bco.bookingcar.application.CarManager;
import bco.bookingcar.application.CustomerManager;
import bco.bookingcar.application.PlanningCarManager;
import bco.bookingcar.application.booking.BcoBookingCarManager;
import bco.bookingcar.application.car.BcoCarManager;
import bco.bookingcar.application.customer.BcoCustomerManager;
import bco.bookingcar.application.planning.BcoPlanningCarManager;
import bco.bookingcar.domain.booking.BcoBookingCar;
import bco.bookingcar.domain.ports.BookingCarEventsDispatcher;
import bco.bookingcar.domain.ports.StoreBookedCars;
import bco.bookingcar.domain.ports.StoreCars;
import bco.bookingcar.domain.ports.StoreCustomers;

@Configuration
public class ApplicationConfiguration {

    @Autowired
    private StoreCars storeCars;

    @Autowired
    private StoreCustomers storeCustomers;

    @Autowired
    private StoreBookedCars storeBookedCars;

    @Autowired
    private BookingCarEventsDispatcher bookingCarEventsDispatcher;

    @Bean
    public CustomerManager customerManager() {
        return new BcoCustomerManager(storeCustomers);
    }

    @Bean
    public CarManager carManager() {
        return new BcoCarManager(storeCars);
    }

    @Bean
    public PlanningCarManager planningCarManager() {
        return new BcoPlanningCarManager(storeCars, storeBookedCars, storeCustomers);
    }

    @Bean
    public BookingCarManager bookingCarManager() {
        return new BcoBookingCarManager(new BcoBookingCar(storeCars, storeBookedCars), storeCars, carManager(), customerManager(), bookingCarEventsDispatcher);
    }
}
