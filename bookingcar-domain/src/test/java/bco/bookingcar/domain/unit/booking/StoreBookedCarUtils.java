package bco.bookingcar.domain.unit.booking;

import bco.bookingcar.domain.booking.BookedCar;
import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.ports.StoreBookedCars;
import bco.bookingcar.domain.shared.Period;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public interface StoreBookedCarUtils {
    static List<Car> changeAndSaveBookedPeriodOfCars(List<Car> cars, List<Period> newPeriods, StoreBookedCars storeBookedCars) {
        var indexCar = new AtomicInteger(0);
        return newPeriods.stream().map(newPeriod -> {
                            var car = cars.get(indexCar.getAndAdd(1));
                            storeBookedCars.add(
                                    BookedCar.builder()
                                            .idCar(car.getId())
                                            .idCustomer(UUID.randomUUID())
                                            .period(newPeriod)
                                            .build());
                            return car;
                        }
                )
               .toList();
    }
}
