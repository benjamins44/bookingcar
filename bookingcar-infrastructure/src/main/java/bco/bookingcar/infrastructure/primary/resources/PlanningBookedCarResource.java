package bco.bookingcar.infrastructure.primary.resources;

import bco.bookingcar.application.planning.PlanningBookedCar;
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
@Schema(name = "Planning Booked Car", description = "A booked car")
public class PlanningBookedCarResource {
    @Schema(description = "Customer of the booked car")
    private CustomerResource customer;

    @Schema(description = "Period of the booked car")
    private PeriodResource period;

    public static PlanningBookedCarResource fromDomain(PlanningBookedCar planningBookedCar) {
        return new PlanningBookedCarResource(
                CustomerResource.fromDomain(planningBookedCar.getCustomer()),
                PeriodResource.fromDomain(planningBookedCar.getPeriod())
        );
    }

    public static List<PlanningBookedCarResource> fromDomain(List<PlanningBookedCar> planningBookedCars) {
        return planningBookedCars.stream().map(PlanningBookedCarResource::fromDomain).collect(Collectors.toList());
    }
}
