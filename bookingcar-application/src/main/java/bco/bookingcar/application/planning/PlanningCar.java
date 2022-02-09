package bco.bookingcar.application.planning;

import java.util.List;

import bco.bookingcar.annotation.DTO;
import bco.bookingcar.domain.car.Car;
import bco.bookingcar.exceptions.BusinessException;
import lombok.Builder;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.With;

import static bco.bookingcar.validation.Assert.field;

@With
@Builder
@Getter
@ToString
@DTO
public class PlanningCar {
    private List<PlanningBookedCar> planningBookedCar;
    private Car car;

    @SneakyThrows({ BusinessException.class })
    public PlanningCar(List<PlanningBookedCar> planningBookedCar, Car car) {
        field("planningBookedCar", planningBookedCar).notNull();
        field("car", car).notNull();

        this.planningBookedCar = planningBookedCar;
        this.car = car;
    }
}
