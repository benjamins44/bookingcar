package bco.bookingcar.domain.unit.booking;

import bco.bookingcar.domain.ports.StoreBookedCars;
import bco.bookingcar.domain.ports.fakes.InMemoryStoreBookedCars;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class StoreBookedCarParameterResolver implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(StoreBookedCars.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return InMemoryStoreBookedCars.builder().build();
    }
}
