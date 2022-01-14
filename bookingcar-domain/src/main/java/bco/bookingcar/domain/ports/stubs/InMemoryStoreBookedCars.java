package bco.bookingcar.domain.ports.stubs;

import bco.bookingcar.annotation.Stub;
import bco.bookingcar.domain.booking.BookedCar;
import bco.bookingcar.domain.ports.StoreBookedCar;
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
@Stub
public class InMemoryStoreBookedCars implements StoreBookedCar {

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
                .collect(Collectors.toList());
    }
}
