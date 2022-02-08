package bco.bookingcar.infrastructure.primary.resources;

import bco.bookingcar.application.planning.PlanningCar;
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
@Schema(name = "Planning", description = "A planning of a car")
public class PlanningCarResource {
    @Schema(description = "List of booked of the car")
    private List<PlanningBookedCarResource> planningBookedCar;

    @Schema(description = "Car information")
    private CarResource car;

    public static PlanningCarResource fromDomain(PlanningCar planningCar) {
        return new PlanningCarResource(
                PlanningBookedCarResource.fromDomain(planningCar.getPlanningBookedCar()),
                CarResource.fromDomain(planningCar.getCar())
        );
    }

    public static List<PlanningCarResource> fromDomain(List<PlanningCar> planningCars) {
        return planningCars.stream().map(PlanningCarResource::fromDomain).collect(Collectors.toList());
    }
}
