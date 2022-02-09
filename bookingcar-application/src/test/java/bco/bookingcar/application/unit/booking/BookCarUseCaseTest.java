package bco.bookingcar.application.unit.booking;

import bco.bookingcar.application.BookCarUseCase;
import bco.bookingcar.application.booking.BookCarPresenter;
import bco.bookingcar.application.booking.BookCarRequest;
import bco.bookingcar.application.booking.BookCarResponse;
import bco.bookingcar.application.booking.BookCarUseCaseImpl;
import bco.bookingcar.application.car.GetCarUseCaseImpl;
import bco.bookingcar.application.customer.GetCustomerUseCaseImpl;
import bco.bookingcar.application.planning.GetPlanningCarPresenter;
import bco.bookingcar.application.planning.PlanningCar;
import bco.bookingcar.domain.BookingCar;
import bco.bookingcar.domain.booking.BookedCar;
import bco.bookingcar.domain.booking.BookingCarImpl;
import bco.bookingcar.domain.booking.CarNotAvailableException;
import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.ports.BookingCarEventsDispatcher;
import bco.bookingcar.domain.ports.StoreBookedCars;
import bco.bookingcar.domain.ports.StoreCars;
import bco.bookingcar.domain.ports.StoreCustomers;
import bco.bookingcar.domain.ports.fakes.InMemoryBookingCarEventsDispatcher;
import bco.bookingcar.domain.shared.Period;
import bco.bookingcar.domain.unit.InjectDomainObjects;
import bco.bookingcar.domain.unit.booking.StoreBookedCarUtils;
import bco.bookingcar.domain.unit.car.CarFactory;
import bco.bookingcar.domain.unit.customer.CustomerFactory;
import bco.bookingcar.domain.unit.shared.PeriodFactory;
import bco.bookingcar.exceptions.BusinessException;
import bco.bookingcar.ports.TransactionManager;
import bco.bookingcar.ports.fakes.InMemoryTransactionManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@InjectDomainObjects
@DisplayName("Booking car test")
public class BookCarUseCaseTest implements BookCarPresenter<BookedCar> {

    private BookCarUseCase bookCarUseCase;
    private BookCarResponse bookCarResponse;
    private StoreCars storeCars;
    private StoreBookedCars storeBookedCars;
    private StoreCustomers storeCustomers;
    private BookingCarEventsDispatcher bookingCarEventsDispatcher;
    private TransactionManager transactionManager;
    private UUID idCustomer;
    private List<Car> cars;

    @BeforeEach
    void setup(StoreCars storeCars, StoreBookedCars storeBookedCars, StoreCustomers storeCustomers, BookingCarEventsDispatcher bookingCarEventsDispatcher, TransactionManager transactionManager) {
        this.storeBookedCars = storeBookedCars;
        this.storeCars = storeCars;
        this.storeCustomers = storeCustomers;
        this.transactionManager = transactionManager;
        this.bookingCarEventsDispatcher = bookingCarEventsDispatcher;
        BookingCar bookingCar = new BookingCarImpl(storeCars, storeBookedCars);
        this.bookCarUseCase = new BookCarUseCaseImpl(bookingCar, new GetCarUseCaseImpl(storeCars), new GetCustomerUseCaseImpl(storeCustomers), bookingCarEventsDispatcher, transactionManager);
    }

    @Override
    public void present(BookCarResponse response) {
        this.bookCarResponse = response;
    }

    @Override
    public BookedCar viewModel() {
        return this.bookCarResponse.getBookedCar();
    }

    @BeforeEach
    void setup() {
        idCustomer = storeCustomers.add(CustomerFactory.build()).getId();
        var nbOfCars = 5;
        cars = storeCars.addAll(CarFactory.buildCars(nbOfCars));
    }

    @Test
    void customer_can_book_an_available_car() throws BusinessException {
        var period = PeriodFactory.build();
        var idCar = cars.get(0).getId();
        bookCarUseCase.execute(
                BookCarRequest.builder()
                        .carId(idCar)
                        .customerId(idCustomer)
                        .period(period)
                        .build(),
                this
        );

        assertThat(storeBookedCars.getAll(period).stream().anyMatch(bookedCar ->
                        bookedCar.getIdCustomer().equals(idCustomer) &&
                                bookedCar.getIdCar().equals(idCar)
                )
        ).isTrue();
    }

    @Test
    void customer_can_not_book_an_unavailable_car() {
        var car = cars.get(0);
        var period = PeriodFactory.build();
        StoreBookedCarUtils.changeAndSaveBookedPeriodOfCars(cars,
                List.of(Period.builder()
                        .startDateTime(period.getStartDateTime().minusHours(2L))
                        .endDateTime(period.getStartDateTime().plusHours(1L))
                        .build()), storeBookedCars
        );

        assertThatThrownBy(() -> bookCarUseCase.execute(
                BookCarRequest.builder()
                        .carId(car.getId())
                        .customerId(idCustomer)
                        .period(period)
                        .build(),
                this
        )).isInstanceOf(CarNotAvailableException.class);
    }

    @Test
    void an_event_is_dispatched_to_anywhere() throws BusinessException {
        var period = PeriodFactory.build();
        var idCar = cars.get(0).getId();
        bookCarUseCase.execute(
                BookCarRequest.builder()
                        .carId(idCar)
                        .customerId(idCustomer)
                        .period(period)
                        .build(),
                this
        );

        assertThat(((InMemoryBookingCarEventsDispatcher) bookingCarEventsDispatcher).hasDispatchedEventWithCarCustomerPeriod(idCar, idCustomer, period)).isTrue();
    }

    @Test
    void a_new_transacation_is_used_to_book_the_car() throws BusinessException {
        var period = PeriodFactory.build();
        var idCar = cars.get(0).getId();
        bookCarUseCase.execute(
                BookCarRequest.builder()
                        .carId(idCar)
                        .customerId(idCustomer)
                        .period(period)
                        .build(),
                this
        );

        assertThat(((InMemoryTransactionManager) transactionManager).hasUsedATransaction()).isTrue();
    }

}

