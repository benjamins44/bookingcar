package bco.bookingcar.application.booking;

import java.util.UUID;

import bco.bookingcar.annotation.DTO;
import bco.bookingcar.domain.shared.Period;
import bco.bookingcar.exceptions.BusinessException;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.With;

import static bco.bookingcar.validation.Assert.field;

@With
@Builder
@Getter
@ToString
@EqualsAndHashCode
@DTO
public class SearchAvailableCarsRequest {
    private Period period;
    private UUID customerId;

    @SneakyThrows({ BusinessException.class })
    public SearchAvailableCarsRequest(Period period, UUID customerId) {
        field("period", period).notNull();
        field("customerId", customerId).notNull();

        this.period = period;
        this.customerId = customerId;
    }
}
