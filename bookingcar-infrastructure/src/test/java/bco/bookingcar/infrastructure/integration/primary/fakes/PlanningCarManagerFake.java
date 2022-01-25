package bco.bookingcar.infrastructure.integration.primary.fakes;

import bco.bookingcar.application.PlanningCarManager;
import bco.bookingcar.application.planning.PlanningCar;
import bco.bookingcar.domain.shared.Period;

import java.util.List;

public class PlanningCarManagerFake implements PlanningCarManager {

    private List<PlanningCar> planning = List.of();

    @Override
    public List<PlanningCar> get(Period period) {
        return planning;
    }

    public void setPlanning(List<PlanningCar> planning) {
        this.planning = planning;
    }
}
