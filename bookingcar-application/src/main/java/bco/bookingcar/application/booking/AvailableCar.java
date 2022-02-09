package bco.bookingcar.application.booking;

import bco.bookingcar.annotation.DTO;
import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.shared.Period;
import bco.bookingcar.exceptions.BusinessException;
import lombok.*;

import static bco.bookingcar.validation.Assert.field;

@With
@Builder
@Getter
@ToString
@EqualsAndHashCode
@DTO
public class AvailableCar {
    private Car car;
    private Period period;

    @SneakyThrows({BusinessException.class})
    public AvailableCar(Car car, Period period) {
        field("car", car).notNull();
        field("period", period).notNull();

        this.car = car;
        this.period = period;
    }
}
