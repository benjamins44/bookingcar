package bco.bookingcar.application.planning;

import java.util.List;
import java.util.stream.Collectors;

import bco.bookingcar.annotation.ApplicationService;
import bco.bookingcar.application.PlanningCarManager;
import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.customer.Customer;
import bco.bookingcar.domain.ports.StoreBookedCar;
import bco.bookingcar.domain.ports.StoreCars;
import bco.bookingcar.domain.ports.StoreCustomers;
import bco.bookingcar.domain.shared.Period;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@ApplicationService
public class BcoPlanningCarManager implements PlanningCarManager {
    private StoreCars storeCars;
    private StoreBookedCar storeBookedCar;
    private StoreCustomers storeCustomers;

    @Override
    public List<PlanningCar> get(Period period) {
        return storeCars.getAll().stream().map(car ->
                        PlanningCar.builder()
                                .car(car)
                                .planningBookedCar(getPlanningBookedCar(car, period)).build()
                ).collect(Collectors.toList());
    }

    private List<PlanningBookedCar> getPlanningBookedCar(Car car, Period period) {
        return storeBookedCar.getBookedCarByCarAndPeriod(car, period).stream()
                .map(bookedCar ->
                        PlanningBookedCar.builder()
                                .period(bookedCar.getPeriod())
                                .customer(storeCustomers.getById(bookedCar.getIdCustomer()).orElse(Customer.builder().firstname("Unknown").lastname("Customer").build()))
                                .build()
                ).collect(Collectors.toList());
    }
}
