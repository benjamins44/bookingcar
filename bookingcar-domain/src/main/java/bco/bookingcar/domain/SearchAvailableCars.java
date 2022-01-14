package bco.bookingcar.domain;

import bco.bookingcar.annotation.DomainService;
import bco.bookingcar.domain.booking.AvailableCar;
import bco.bookingcar.domain.booking.SearchAvailableCarsCriterias;

import java.util.List;

@DomainService
public interface SearchAvailableCars {
    List<AvailableCar> search(SearchAvailableCarsCriterias criterias);
}
