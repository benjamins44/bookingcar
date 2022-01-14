package bco.bookingcar.domain.booking;

import bco.bookingcar.annotation.ValueObject;
import bco.bookingcar.domain.shared.Period;
import lombok.*;

import static org.apache.commons.lang3.Validate.notNull;

@With
@Builder
@Getter
@ToString
@EqualsAndHashCode
@ValueObject
public class SearchAvailableCarsCriterias {
    private Period period;

    public SearchAvailableCarsCriterias(Period period) {
        notNull(period, "The startDate is mandatory");

        this.period = period;
    }
}
