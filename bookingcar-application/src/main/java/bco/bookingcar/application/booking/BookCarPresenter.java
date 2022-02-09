package bco.bookingcar.application.booking;

public interface BookCarPresenter<T> {
    void present(BookCarResponse response);

    T viewModel();
}
