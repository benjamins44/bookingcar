package bco.bookingcar.application;

import bco.bookingcar.application.planning.GetPlanningCarPresenter;
import bco.bookingcar.application.planning.GetPlanningCarRequest;

public interface GetPlanningCarUseCase {
    void execute(GetPlanningCarRequest request, GetPlanningCarPresenter presenter);
}
