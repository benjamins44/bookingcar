package bco.bookingcar.infrastructure.primary.planning;

import org.springframework.stereotype.Component;

import bco.bookingcar.application.planning.GetPlanningCarPresenter;
import bco.bookingcar.application.planning.GetPlanningCarResponse;

@Component
public class GetPlanningCarResourcePresenter implements GetPlanningCarPresenter<GetPlanningCarResultResource> {

    private GetPlanningCarResultResource viewModel;

    @Override
    public void present(GetPlanningCarResponse response) {
        this.viewModel = GetPlanningCarResultResource.builder()
                .result(PlanningCarResource.fromDomain(response.getPlanningCarList()))
                .period(response.getSearchPeriod()).build();
    }

    @Override
    public GetPlanningCarResultResource viewModel() {
        return viewModel;
    }
}
