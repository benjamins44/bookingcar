package bco.bookingcar.application.planning;

import bco.bookingcar.annotation.DTO;
import bco.bookingcar.domain.shared.Period;
import bco.bookingcar.exceptions.BusinessException;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.ToString;

import static bco.bookingcar.validation.Assert.field;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@DTO
public class GetPlanningCarRequest {
    private Period period;

    @SneakyThrows({ BusinessException.class })
    public GetPlanningCarRequest(Period period) {
        field("period", period).notNull();

        this.period = period;
    }
}
