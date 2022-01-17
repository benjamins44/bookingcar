package bco.bookingcar.application.unit.booking;

import bco.bookingcar.application.booking.SearchAvailableCarsCriterias;
import bco.bookingcar.domain.unit.shared.PeriodFactory;

public interface SearchAvailableCarsCriteriasFactory {
    static SearchAvailableCarsCriterias build() {
        return SearchAvailableCarsCriterias.builder().period(PeriodFactory.build()).build();
    }
}
