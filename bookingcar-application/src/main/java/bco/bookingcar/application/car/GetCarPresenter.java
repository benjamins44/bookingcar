package bco.bookingcar.application.car;

public interface GetCarPresenter<T> {
    void present(GetCarResponse response);

    T viewModel();
}
