package bco.bookingcar.infrastructure.primary.resources;

import bco.bookingcar.application.booking.AvailableCar;
import bco.bookingcar.exceptions.BusinessException;
import bco.bookingcar.infrastructure.primary.car.CarResource;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(name = "Available Car", description = "A car available on the period")
public class AvailableCarResource {
    @Schema(description = "Available car")
    private CarResource car;

    @Schema(description = "Available period")
    private PeriodResource period;

    public static AvailableCarResource fromDomain(AvailableCar availableCar) {
        return new AvailableCarResource(CarResource.fromDomain(availableCar.getCar()), PeriodResource.fromDomain(availableCar.getPeriod()));
    }

    public static List<AvailableCarResource> fromDomain(List<AvailableCar> availableCars) {
        return availableCars.stream().map(AvailableCarResource::fromDomain).collect(Collectors.toList());
    }

    public AvailableCar toDomain() throws BusinessException {
        return new AvailableCar(this.getCar().toDomain(), this.getPeriod().toDomain());
    }
}
