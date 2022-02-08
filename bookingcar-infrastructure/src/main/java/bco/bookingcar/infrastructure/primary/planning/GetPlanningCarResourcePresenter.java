package bco.bookingcar.infrastructure.primary.planning;

import java.util.List;

import org.springframework.stereotype.Component;

import bco.bookingcar.application.planning.GetPlanningCarPresenter;
import bco.bookingcar.application.planning.GetPlanningCarResponse;

@Component
public class GetPlanningCarResourcePresenter implements GetPlanningCarPresenter<List<PlanningCarResource>> {

    private List<PlanningCarResource> viewModel;

    @Override
    public void present(GetPlanningCarResponse response) {
        this.viewModel = PlanningCarResource.fromDomain(response.getPlanningCarList());
    }

    @Override
    public List<PlanningCarResource> viewModel() {
        return viewModel;
    }
}
