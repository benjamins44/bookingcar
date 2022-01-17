package bco.bookingcar.application.booking;

import bco.bookingcar.annotation.DTO;
import bco.bookingcar.domain.shared.Period;
import lombok.*;

import java.util.UUID;

import static org.apache.commons.lang3.Validate.notNull;

@With
@Builder
@Getter
@ToString
@EqualsAndHashCode
@DTO
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
