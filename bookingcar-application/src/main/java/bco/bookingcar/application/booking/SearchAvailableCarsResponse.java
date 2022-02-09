package bco.bookingcar.application.booking;

import java.util.List;

import bco.bookingcar.annotation.DTO;
import bco.bookingcar.domain.customer.Customer;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@DTO
public class SearchAvailableCarsResponse {
    private List<AvailableCar> availableCarList;
    private SearchAvailableCarsRequest request;
}
