package bco.bookingcar.domain.shared;

import bco.bookingcar.annotation.ValueObject;
import bco.bookingcar.exceptions.BusinessException;
import lombok.*;

import java.time.ZonedDateTime;

import static bco.bookingcar.validation.Assert.field;

@With
@Builder
@Getter
@ToString
@EqualsAndHashCode
@ValueObject
public class Period {
    private ZonedDateTime startDateTime;
    private ZonedDateTime endDateTime;

    @SneakyThrows({BusinessException.class})
    public Period(ZonedDateTime startDateTime, ZonedDateTime endDateTime) {
        field("startDateTime", startDateTime).notNull();
        field("endDateTime", endDateTime).notNull();
        if (!endDateTime.isAfter(startDateTime)) {
            throw new StartDateAfterEndDateException(startDateTime, endDateTime);
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
