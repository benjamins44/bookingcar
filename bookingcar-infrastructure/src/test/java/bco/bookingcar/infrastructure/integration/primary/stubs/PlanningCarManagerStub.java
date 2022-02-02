package bco.bookingcar.infrastructure.integration.primary.stubs;

import bco.bookingcar.annotation.Stub;
import bco.bookingcar.application.PlanningCarManager;
import bco.bookingcar.application.planning.PlanningCar;
import bco.bookingcar.domain.shared.Period;

import java.util.List;

@Stub
public class PlanningCarManagerStub implements PlanningCarManager {

    private List<PlanningCar> planning = List.of();

    @Override
    public List<PlanningCar> get(Period period) {
        return planning;
    }

    public void setPlanning(List<PlanningCar> planning) {
        this.planning = planning;
    }
}
