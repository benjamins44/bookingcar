package bco.bookingcar.application.acceptance.stepdefs;

import bco.bookingcar.domain.booking.AvailableCar;
import bco.bookingcar.domain.booking.BookedCar;
import bco.bookingcar.domain.customer.Customer;
import bco.bookingcar.domain.shared.Period;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class TestContext {
    private Optional<Customer> customer;
    private List<AvailableCar> availableCars;
    private BookedCar bookedCar;
    private Period bookingPeriod;
}
