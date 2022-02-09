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
public class BookCarRequest {
    private UUID carId;
    private UUID customerId;
    private Period period;

    @SneakyThrows({ BusinessException.class })
    public BookCarRequest(UUID carId, UUID customerId, Period period) {
        field("carId", carId).notNull();
        field("customerId", customerId).notNull();
        field("period", period).notNull();

        this.carId = carId;
        this.customerId = customerId;
        this.period = period;
    }
}
