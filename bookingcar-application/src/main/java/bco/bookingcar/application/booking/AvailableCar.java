package bco.bookingcar.application.booking;

import bco.bookingcar.annotation.DTO;
import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.shared.Period;
import lombok.*;

import static org.apache.commons.lang3.Validate.notNull;

@With
@Builder
@Getter
@ToString
@EqualsAndHashCode
@DTO
public class AvailableCar {
    private Car car;
    private Period period;

    public AvailableCar(Car car, Period period) {
        notNull(car, "The car is mandatory");
        notNull(period, "The startDate is mandatory");

        this.car = car;
        this.period = period;
    }
}
