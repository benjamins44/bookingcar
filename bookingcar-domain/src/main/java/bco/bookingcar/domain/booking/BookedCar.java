package bco.bookingcar.domain.booking;

import bco.bookingcar.annotation.DomainEntity;
import bco.bookingcar.domain.shared.Period;
import lombok.*;

import java.util.UUID;

import static org.apache.commons.lang3.Validate.notNull;

@With
@Builder
@Getter
@ToString
@EqualsAndHashCode(of = {"id"})
@DomainEntity
public class BookedCar {
    private UUID id;
    private UUID idCustomer;
    private UUID idCar;
    private Period period;

    public BookedCar(UUID id, UUID idCustomer, UUID idCar, Period period) {
        notNull(idCustomer, "The idCustomer is mandatory");
        notNull(idCar, "The idCar is mandatory");
        notNull(period, "The startDate is mandatory");

        this.id = id;
        this.idCar = idCar;
        this.period = period;
        this.idCustomer = idCustomer;
    }
}
