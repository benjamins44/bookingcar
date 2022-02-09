package bco.bookingcar.application.acceptance.stepdefs;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import bco.bookingcar.application.booking.BookCar;
import bco.bookingcar.application.booking.BookCarRequest;
import bco.bookingcar.application.booking.SearchAvailableCars;
import bco.bookingcar.application.booking.SearchAvailableCarsRequest;
import bco.bookingcar.domain.booking.BookedCar;
import bco.bookingcar.domain.ports.StoreBookedCars;
import bco.bookingcar.domain.shared.Period;
import io.cucumber.java8.En;

import static org.assertj.core.api.Assertions.assertThat;

public class BookedCarStepdefs implements En {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final ZoneId timeZone = ZoneId.systemDefault();

    public BookedCarStepdefs(StoreBookedCars storeBookedCars, BookCar bookCar, SearchAvailableCars searchAvailableCars, TestContext testContext) {
        Given("^The car \"([^\"]*)\" is booked between \"([^\"]*)\" and \"([^\"]*)\"$", (String idCar, String startDate, String endDate) ->
                storeBookedCars.add(
                        BookedCar.builder()
                                .idCar(UUID.fromString(idCar))
                                .idCustomer(UUID.randomUUID())
                                .period(Period.builder()
                                        .startDateTime(toZonedDateTime(startDate))
                                        .endDateTime(toZonedDateTime(endDate))
                                        .build()
                                )
                                .build()
                )
        );
        When("^I'm looking for available cars between \"([^\"]*)\" and \"([^\"]*)\"$", (String startDate, String endDate) -> {
            var availableCars = searchAvailableCars.searchAvailableCars(
                    SearchAvailableCarsRequest.builder()
                            .period(Period.builder()
                                    .startDateTime(toZonedDateTime(startDate))
                                    .endDateTime(toZonedDateTime(endDate))
                                    .build()
                            )
                            .customerId(UUID.randomUUID())
                            .build()
            );
            testContext.setAvailableCars(availableCars);
        });
        Then("^The car \"([^\"]*)\" is available$", (String idCar) ->
                assertThat(
                        testContext.getAvailableCars().stream()
                                .map(availableCar -> availableCar.getCar().getId())
                                .anyMatch(id -> UUID.fromString(idCar).equals(id))
                ).isTrue()
        );
        When("^I book the car \"([^\"]*)\" between \"([^\"]*)\" and \"([^\"]*)\"$", (String idCar, String startDate, String endDate) -> {
            var bookingPeriod = Period.builder()
                    .startDateTime(toZonedDateTime(startDate))
                    .endDateTime(toZonedDateTime(endDate))
                    .build();
            var bookedCar = bookCar.book(
                    BookCarRequest.builder()
                            .carId(UUID.fromString(idCar))
                            .customerId(testContext.getCustomer().get().getId())
                            .period(bookingPeriod)
                            .build()
            );
            testContext.setBookedCar(bookedCar);
            testContext.setBookingPeriod(bookingPeriod);
        });
        Then("^This car is booked for me$", () -> {
            var bookedCars = storeBookedCars.getAll(testContext.getBookingPeriod());
            assertThat(
                    bookedCars.stream()
                            .anyMatch(bookedCar -> bookedCar.getIdCustomer().equals(testContext.getCustomer().get().getId()))
            ).isTrue();
        });
    }

    private ZonedDateTime toZonedDateTime(String strDate) {
        return LocalDateTime.parse(strDate, formatter).atZone(timeZone);
    }
}
