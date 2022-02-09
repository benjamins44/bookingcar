package bco.bookingcar.application.car;

import java.util.UUID;

import bco.bookingcar.annotation.DTO;
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
public class GetCarRequest {
    private UUID idCar;

    @SneakyThrows({ BusinessException.class })
    public GetCarRequest(UUID idCar) {
        field("idCar", idCar).notNull();

        this.idCar = idCar;
    }
}
