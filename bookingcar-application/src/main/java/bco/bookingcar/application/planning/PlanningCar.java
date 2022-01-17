package bco.bookingcar.application.planning;

import java.util.List;

import bco.bookingcar.annotation.DTO;
import bco.bookingcar.domain.car.Car;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.With;

import static org.apache.commons.lang3.Validate.notNull;

@With
@Builder
@Getter
@ToString
@DTO
public class PlanningCar {
    private List<PlanningBookedCar> planningBookedCar;
    private Car car;

    public PlanningCar(List<PlanningBookedCar> planningBookedCar, Car car) {
        notNull(planningBookedCar, "The planningBookedCar is mandatory");
        notNull(car, "The car is mandatory");

        this.planningBookedCar = planningBookedCar;
        this.car = car;
    }
}
