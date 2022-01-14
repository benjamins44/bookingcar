package bco.bookingcar.domain.shared;

import bco.bookingcar.annotation.ValueObject;
import lombok.*;

import java.time.ZonedDateTime;

import static org.apache.commons.lang3.Validate.notNull;

@With
@Builder
@Getter
@ToString
@EqualsAndHashCode
@ValueObject
public class Period {
    private ZonedDateTime startDateTime;
    private ZonedDateTime endDateTime;

    public Period(ZonedDateTime startDateTime, ZonedDateTime endDateTime) {
        notNull(startDateTime, "The startDateTime is mandatory");
        notNull(endDateTime, "The endDateTime is mandatory");
        if (!endDateTime.isAfter(startDateTime)) {
            throw new IllegalArgumentException("End date must be after start date");
        }
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public boolean hasIntersectionWith(Period period) {
        return (
                this.equals(period) ||
                        this.getStartDateTime().isBefore(period.getStartDateTime()) &&
                                this.getEndDateTime().isAfter(period.getStartDateTime())) ||
                (this.getStartDateTime().isBefore(period.getEndDateTime()) &&
                        this.getEndDateTime().isAfter(period.getEndDateTime()));
    }
}
