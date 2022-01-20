package bco.bookingcar.infrastructure.primary.resources;

import bco.bookingcar.domain.shared.Period;
import bco.bookingcar.utils.ZonedDateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import static bco.bookingcar.utils.ZonedDateUtils.toZonedDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(name = "Period", description = "A period")
public class PeriodResource {
    @Schema(description = "Start date and time of the period")
    private String startDateTime;

    @Schema(description = "End date and time of the period")
    private String endDateTime;

    public static PeriodResource fromDomain(Period period) {
        return new PeriodResource(ZonedDateUtils.toString(period.getStartDateTime()), ZonedDateUtils.toString(period.getEndDateTime()));
    }

    public Period toDomain() {
        return new Period(toZonedDateTime(this.getStartDateTime()), toZonedDateTime(this.getEndDateTime()));
    }
}

