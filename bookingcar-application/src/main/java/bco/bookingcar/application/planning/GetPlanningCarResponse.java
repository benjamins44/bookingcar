package bco.bookingcar.application.planning;

import java.util.List;

import bco.bookingcar.annotation.DTO;
import bco.bookingcar.domain.shared.Period;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@DTO
public class GetPlanningCarResponse {
    private List<PlanningCar> planningCarList;
    private Period searchPeriod;
}
