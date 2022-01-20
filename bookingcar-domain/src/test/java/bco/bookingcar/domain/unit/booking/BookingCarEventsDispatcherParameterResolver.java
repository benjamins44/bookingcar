package bco.bookingcar.domain.unit.booking;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import bco.bookingcar.domain.ports.BookingCarEventsDispatcher;
import bco.bookingcar.domain.ports.fakes.InMemoryBookingCarEventsDispatcher;

public class BookingCarEventsDispatcherParameterResolver implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(BookingCarEventsDispatcher.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return InMemoryBookingCarEventsDispatcher.builder().build();
    }
}
