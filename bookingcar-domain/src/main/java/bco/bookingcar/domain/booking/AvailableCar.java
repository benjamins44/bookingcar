package bco.bookingcar.domain.booking;

import bco.bookingcar.annotation.ValueObject;
import bco.bookingcar.domain.shared.Period;
import lombok.*;

import java.util.UUID;

import static org.apache.commons.lang3.Validate.notNull;

@With
@Builder
@Getter
@ToString
@EqualsAndHashCode
@ValueObject
public class AvailableCar {
    private UUID idCar;
    private Period period;

    public AvailableCar(UUID idCar, Period period) {
        notNull(idCar, "The idCar is mandatory");
        notNull(period, "The startDate is mandatory");

        this.idCar = idCar;
        this.period = period;
    }
}
