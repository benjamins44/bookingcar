package bco.bookingcar.application.acceptance;

import bco.bookingcar.application.booking.BcoBookingCarManager;
import bco.bookingcar.application.car.BcoCarManager;
import bco.bookingcar.application.customer.BcoCustomerManager;
import bco.bookingcar.domain.booking.BcoBookingCar;
import bco.bookingcar.domain.ports.fakes.InMemoryBookingCarEventsDispatcher;
import bco.bookingcar.domain.ports.fakes.InMemoryStoreBookedCars;
import bco.bookingcar.domain.ports.fakes.InMemoryStoreCars;
import bco.bookingcar.domain.ports.fakes.InMemoryStoreCustomers;
import cucumber.runtime.java.picocontainer.PicoFactory;

public class CustomPicoFactory extends PicoFactory {

    public CustomPicoFactory() {
        addClass(BcoBookingCarManager.class);
        addClass(BcoBookingCar.class);
        addClass(BcoCarManager.class);
        addClass(BcoCustomerManager.class);
        addClass(InMemoryStoreCustomers.class);
        addClass(InMemoryStoreCars.class);
        addClass(InMemoryStoreBookedCars.class);
        addClass(InMemoryBookingCarEventsDispatcher.class);
    }
}

