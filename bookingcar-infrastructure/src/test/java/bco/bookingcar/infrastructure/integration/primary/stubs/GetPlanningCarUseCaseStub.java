package bco.bookingcar.infrastructure.integration.primary.stubs;

import bco.bookingcar.annotation.Stub;
import bco.bookingcar.application.GetPlanningCarUseCase;
import bco.bookingcar.application.planning.GetPlanningCarPresenter;
import bco.bookingcar.application.planning.GetPlanningCarRequest;
import bco.bookingcar.application.planning.GetPlanningCarResponse;
import bco.bookingcar.application.planning.PlanningCar;
import bco.bookingcar.domain.shared.Period;

import java.util.List;

@Stub
public class GetPlanningCarUseCaseStub implements GetPlanningCarUseCase {

    private List<PlanningCar> planning = List.of();

    public void setPlanning(List<PlanningCar> planning) {
        this.planning = planning;
    }

    @Override
    public void execute(GetPlanningCarRequest request, GetPlanningCarPresenter presenter) {
        presenter.present(
                GetPlanningCarResponse.builder()
                        .planningCarList(planning)
                        .build()
        );
    }
}
