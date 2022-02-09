package bco.bookingcar.application.acceptance;

import bco.bookingcar.application.booking.BookCarUseCaseImpl;
import bco.bookingcar.application.booking.SearchAvailableCarsUseCaseImpl;
import bco.bookingcar.application.car.GetCarUseCaseImpl;
import bco.bookingcar.application.customer.GetCustomerUseCaseImpl;
import bco.bookingcar.domain.booking.BookingCarImpl;
import bco.bookingcar.domain.ports.fakes.InMemoryBookingCarEventsDispatcher;
import bco.bookingcar.domain.ports.fakes.InMemoryStoreBookedCars;
import bco.bookingcar.domain.ports.fakes.InMemoryStoreCars;
import bco.bookingcar.domain.ports.fakes.InMemoryStoreCustomers;
import bco.bookingcar.ports.fakes.InMemoryTransactionManager;
import io.cucumber.core.backend.ObjectFactory;
import io.cucumber.picocontainer.PicoFactory;

public class CustomPicoFactory implements ObjectFactory {

    private final PicoFactory delegate = new PicoFactory();

    public CustomPicoFactory() {
        addClass(BookCarUseCaseImpl.class);
        addClass(SearchAvailableCarsUseCaseImpl.class);
        addClass(BookingCarImpl.class);
        addClass(GetCarUseCaseImpl.class);
        addClass(GetCustomerUseCaseImpl.class);
        addClass(InMemoryStoreCustomers.class);
        addClass(InMemoryStoreCars.class);
        addClass(InMemoryStoreBookedCars.class);
        addClass(InMemoryBookingCarEventsDispatcher.class);
        addClass(InMemoryTransactionManager.class);
    }

    @Override
    public void start() {
        delegate.start();
    }

    @Override
    public void stop() {
        delegate.stop();
    }

    @Override
    public boolean addClass(Class<?> aClass) {
        return delegate.addClass(aClass);
    }

    @Override
    public <T> T getInstance(Class<T> aClass) {
        return delegate.getInstance(aClass);
    }
}
