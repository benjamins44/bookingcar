package bco.bookingcar.domain.unit.booking;

import bco.bookingcar.domain.ports.StoreBookedCar;
import bco.bookingcar.domain.ports.stubs.InMemoryStoreBookedCars;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class StoreBookedCarParameterResolver implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(StoreBookedCar.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return InMemoryStoreBookedCars.builder().build();
    }
}
