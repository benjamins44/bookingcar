package bco.bookingcar.application.primary;

import java.util.List;

import bco.bookingcar.application.planning.PlanningCar;
import bco.bookingcar.domain.shared.Period;

public interface PlanningCarManager {
    List<PlanningCar> get(Period build);
}
