package bco.bookingcar.domain.unit;

import bco.bookingcar.domain.unit.booking.BookingCarEventsDispatcherParameterResolver;
import bco.bookingcar.domain.unit.booking.StoreBookedCarParameterResolver;
import bco.bookingcar.domain.unit.car.StoreCarsParameterResolver;
import bco.bookingcar.domain.unit.customer.StoreCustomersParameterResolver;
import bco.bookingcar.ports.TransactionManagerParameterResolver;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.CONSTRUCTOR})
@ExtendWith({
        StoreCarsParameterResolver.class,
        StoreCustomersParameterResolver.class,
        StoreBookedCarParameterResolver.class,
        BookingCarEventsDispatcherParameterResolver.class,
        TransactionManagerParameterResolver.class
})
public @interface InjectDomainObjects {
}

