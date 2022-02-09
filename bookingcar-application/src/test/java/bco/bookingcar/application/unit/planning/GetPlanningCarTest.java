package bco.bookingcar.application.unit.planning;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bco.bookingcar.application.GetPlanningCarUseCase;
import bco.bookingcar.application.planning.GetPlanningCarUseCaseImpl;
import bco.bookingcar.application.planning.GetPlanningCarPresenter;
import bco.bookingcar.application.planning.GetPlanningCarRequest;
import bco.bookingcar.application.planning.GetPlanningCarResponse;
import bco.bookingcar.application.planning.PlanningCar;
import bco.bookingcar.domain.ports.StoreBookedCars;
import bco.bookingcar.domain.ports.StoreCars;
import bco.bookingcar.domain.ports.StoreCustomers;
import bco.bookingcar.domain.shared.Period;
import bco.bookingcar.domain.unit.InjectDomainObjects;
import bco.bookingcar.domain.unit.booking.StoreBookedCarUtils;
import bco.bookingcar.domain.unit.car.CarFactory;
import bco.bookingcar.domain.unit.shared.PeriodFactory;

import static org.assertj.core.api.Assertions.assertThat;

@InjectDomainObjects
@DisplayName("Get planning car  test")
public class GetPlanningCarTest implements GetPlanningCarPresenter<List<PlanningCar>> {

    private StoreCars storeCars;
    private StoreBookedCars storeBookedCars;
    private GetPlanningCarUseCase getPlanningCarUseCase;
    private GetPlanningCarResponse getPlanningCarResponse;

    @Override
    public void present(GetPlanningCarResponse response) {
        this.getPlanningCarResponse = response;
    }

    @Override
    public List<PlanningCar> viewModel() {
        return this.getPlanningCarResponse.getPlanningCarList();
    }

    @BeforeEach
    void setup(StoreCars storeCars, StoreBookedCars storeBookedCars, StoreCustomers storeCustomers) {
        this.storeCars = storeCars;
        this.storeBookedCars = storeBookedCars;
        getPlanningCarUseCase = new GetPlanningCarUseCaseImpl(storeCars, storeBookedCars, storeCustomers);
    }

    @Test
    void can_get_planning_of_one_car_without_booked() {
        var nbOfCars = 1;
        storeCars.addAll(CarFactory.buildCars(nbOfCars));

        getPlanningCarUseCase.execute(GetPlanningCarRequest.builder().period(PeriodFactory.build()).build(), this);

        assertThat(viewModel().size()).isEqualTo(1);
        assertThat(viewModel().get(0).getPlanningBookedCar().isEmpty()).isTrue();
    }

    @Test
    void can_get_planning_of_one_car_with_one_booked() {
        var nbOfCars = 1;
        var period = PeriodFactory.build();
        var cars = storeCars.addAll(CarFactory.buildCars(nbOfCars));
        StoreBookedCarUtils.changeAndSaveBookedPeriodOfCars(cars,
                List.of(Period.builder().startDateTime(period.getStartDateTime().minusHours(2L)).endDateTime(period.getStartDateTime().plusHours(1L)).build()),
                storeBookedCars);

        getPlanningCarUseCase.execute(GetPlanningCarRequest.builder().period(period).build(), this);

        assertThat(viewModel().size()).isEqualTo(1);
        assertThat(viewModel().get(0).getPlanningBookedCar().size()).isEqualTo(1);
    }

    @Test
    void can_get_planning_of_two_cars_with_one_booked_on_each() {
        var nbOfCars = 2;
        var period = PeriodFactory.build();
        var cars = storeCars.addAll(CarFactory.buildCars(nbOfCars));
        StoreBookedCarUtils.changeAndSaveBookedPeriodOfCars(cars,
                List.of(Period.builder().startDateTime(period.getStartDateTime().minusHours(2L)).endDateTime(period.getStartDateTime().plusHours(1L)).build(),
                        Period.builder().startDateTime(period.getEndDateTime().minusHours(2L)).endDateTime(period.getEndDateTime().plusHours(1L)).build()),
                storeBookedCars);

        getPlanningCarUseCase.execute(GetPlanningCarRequest.builder().period(period).build(), this);

        assertThat(viewModel().size()).isEqualTo(2);
        assertThat(viewModel().get(0).getPlanningBookedCar().size()).isEqualTo(1);
        assertThat(viewModel().get(1).getPlanningBookedCar().size()).isEqualTo(1);
    }

    @DisplayName("period is in the response")
    @Test
    void customer_is_in_response() {
        var period = PeriodFactory.build();

        getPlanningCarUseCase.execute(GetPlanningCarRequest.builder().period(period).build(), this);

        assertThat(this.getPlanningCarResponse.getSearchPeriod()).isEqualTo(period);
    }
}
