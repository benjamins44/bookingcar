package bco.bookingcar.application.planning;

public interface GetPlanningCarPresenter<T> {
    void present(GetPlanningCarResponse response);

    T viewModel();
}
