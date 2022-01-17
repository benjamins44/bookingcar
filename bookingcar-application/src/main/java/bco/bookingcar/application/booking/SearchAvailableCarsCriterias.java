package bco.bookingcar.application.booking;

import bco.bookingcar.annotation.DTO;
import bco.bookingcar.domain.shared.Period;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.With;

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
        notNull(period, "The startDate is mandatory");

        this.period = period;
    }
}
