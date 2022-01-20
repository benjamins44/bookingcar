package bco.bookingcar.application.booking;

import bco.bookingcar.annotation.DTO;
import bco.bookingcar.domain.shared.Period;
import lombok.*;

import static org.apache.commons.lang3.Validate.notNull;

@With
@Builder
@Getter
@ToString
@EqualsAndHashCode
@DTO
public class SearchAvailableCarsCriterias {
    private Period period;

    public SearchAvailableCarsCriterias(Period period) {
        notNull(period, "The period is mandatory");

        this.period = period;
    }
}
