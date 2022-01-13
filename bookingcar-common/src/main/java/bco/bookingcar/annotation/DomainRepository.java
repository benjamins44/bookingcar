package bco.bookingcar.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
@Inherited
public @interface DomainRepository {
}
