package bco.reservationvoitures.socle.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
@Inherited
public @interface DomainRepository {
}
