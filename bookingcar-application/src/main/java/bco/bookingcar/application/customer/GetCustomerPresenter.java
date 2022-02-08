package bco.bookingcar.application.customer;

public interface GetCustomerPresenter<T> {
    void present(GetCustomerResponse response);

    T viewModel();
}
