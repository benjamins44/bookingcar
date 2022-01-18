package bco.bookingcar.domain.booking;

import bco.bookingcar.annotation.DomainEntity;
import bco.bookingcar.domain.shared.Period;
import lombok.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private List<BookedCarCreatedEvent> createdEvents;

    public BookedCar(UUID id, UUID idCustomer, UUID idCar, Period period, List<BookedCarCreatedEvent> createdEvents) {
        notNull(idCustomer, "The idCustomer is mandatory");
        notNull(idCar, "The idCar is mandatory");
        notNull(period, "The startDate is mandatory");

        this.id = id;
        this.idCar = idCar;
        this.period = period;
        this.idCustomer = idCustomer;
        this.createdEvents = (createdEvents != null) ? createdEvents : List.of();
    }

    public BookedCar addEvent(final BookedCarCreatedEvent event) {
        return this.withCreatedEvents(
                Stream.concat(createdEvents.stream(), List.of(event).stream()).collect(Collectors.toList())
        );
    }
}
