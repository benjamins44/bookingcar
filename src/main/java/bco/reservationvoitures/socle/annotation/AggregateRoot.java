package bco.reservationvoitures.socle.annotation;

import net.bytebuddy.implementation.attribute.AnnotationRetention;
import org.jboss.jandex.AnnotationTarget;

import java.lang.annotation.*;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
@Inherited
public @interface AggregateRoot {
}
