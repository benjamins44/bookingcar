package bco.bookingcar.application.car;

import bco.bookingcar.annotation.DTO;
import bco.bookingcar.domain.car.Car;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@DTO
public class GetCarResponse {
    private Car car;
}
