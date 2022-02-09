package bco.bookingcar.domain.ports.fakes;

import bco.bookingcar.annotation.Fake;
import bco.bookingcar.domain.booking.BookedCar;
import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.ports.StoreBookedCars;
import bco.bookingcar.domain.shared.Period;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@Fake
public class InMemoryStoreBookedCars implements StoreBookedCars {

    private final Map<UUID, BookedCar> bookedCarMap = new HashMap<>();

    @Override
    public BookedCar add(BookedCar bookedCar) {
        var saveBookedCar = bookedCar.getId() == null ? bookedCar.withId(UUID.randomUUID()) : bookedCar;
        bookedCarMap.put(saveBookedCar.getId(), saveBookedCar);
        return saveBookedCar;
    }

    @Override
    public List<BookedCar> getAll(Period period) {
        return bookedCarMap.values()
                .stream()
                .filter(bookedCar -> bookedCar.getPeriod().hasIntersectionWith(period))
               .toList();
    }

    @Override
    public List<BookedCar> getBookedCarByCarAndPeriod(Car car, Period period) {
        return getAll(period).stream().filter(bookedCar -> bookedCar.getIdCar().equals(car.getId())).toList();
    }
}
