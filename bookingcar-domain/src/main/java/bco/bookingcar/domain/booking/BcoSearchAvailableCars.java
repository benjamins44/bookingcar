package bco.bookingcar.domain.booking;

import bco.bookingcar.domain.SearchAvailableCars;
import bco.bookingcar.domain.ports.StoreBookedCar;
import bco.bookingcar.domain.ports.StoreCars;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class BcoSearchAvailableCars implements SearchAvailableCars {

    private StoreCars storeCars;
    private StoreBookedCar storeBookedCar;

    @Override
    public List<AvailableCar> search(SearchAvailableCarsCriterias criterias) {
        return storeCars
                .getAll()
                .stream()
                .filter(car -> car.isNotBookedIn(storeBookedCar, criterias.getPeriod()))
                .map(car -> AvailableCar.builder()
                        .idCar(car.getId())
                        .period(criterias.getPeriod())
                        .build()
                ).collect(Collectors.toList());
    }
}
