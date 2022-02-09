package bco.bookingcar.application.planning;

import java.util.List;
import java.util.stream.Collectors;

import bco.bookingcar.annotation.ApplicationService;
import bco.bookingcar.application.GetPlanningCarUseCase;
import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.customer.Customer;
import bco.bookingcar.domain.ports.StoreBookedCars;
import bco.bookingcar.domain.ports.StoreCars;
import bco.bookingcar.domain.ports.StoreCustomers;
import bco.bookingcar.domain.shared.Period;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@ApplicationService
public class GetPlanningCarUseCaseImpl implements GetPlanningCarUseCase {
    private StoreCars storeCars;
    private StoreBookedCars storeBookedCars;
    private StoreCustomers storeCustomers;

    @Override
    public void execute(GetPlanningCarRequest request, GetPlanningCarPresenter presenter) {
        var planningCarList = storeCars.getAll().stream()
                .map(car -> PlanningCar.builder().car(car).planningBookedCar(getPlanningBookedCar(car, request.getPeriod())).build())
               .toList();
        presenter.present(
                GetPlanningCarResponse.builder()
                        .planningCarList(planningCarList)
                        .searchPeriod(request.getPeriod())
                        .build()
        );
    }

    private List<PlanningBookedCar> getPlanningBookedCar(Car car, Period period) {
        return storeBookedCars.getBookedCarByCarAndPeriod(car, period).stream().map(bookedCar -> PlanningBookedCar.builder().period(bookedCar.getPeriod())
                .customer(storeCustomers.getById(bookedCar.getIdCustomer()).orElse(Customer.builder().firstname("Unknown").lastname("Customer").build()))
                .build()).toList();
    }

}
