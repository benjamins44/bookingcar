package bco.bookingcar.application.acceptance.stepdefs;

import bco.bookingcar.application.primary.BookingCarManager;
import bco.bookingcar.application.booking.AvailableCar;
import bco.bookingcar.domain.booking.BookedCar;
import bco.bookingcar.application.booking.SearchAvailableCarsCriterias;
import bco.bookingcar.domain.secondary.StoreBookedCar;
import bco.bookingcar.domain.secondary.StoreCars;
import bco.bookingcar.domain.shared.Period;
import cucumber.api.java8.En;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class BookedCarStepdefs implements En {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final ZoneId timeZone = ZoneId.systemDefault();

    public BookedCarStepdefs(StoreBookedCar storeBookedCar, StoreCars storeCars, BookingCarManager bookingCarManager, TestContext testContext) {
        Given("^The car \"([^\"]*)\" is booked between \"([^\"]*)\" and \"([^\"]*)\"$", (String idCar, String startDate, String endDate) ->
                storeBookedCar.add(
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
            var availableCars = bookingCarManager.search(
                    SearchAvailableCarsCriterias.builder()
                            .period(Period.builder()
                                    .startDateTime(toZonedDateTime(startDate))
                                    .endDateTime(toZonedDateTime(endDate))
                                    .build()
                            )
                            .build()
            );
            testContext.setAvailableCars(availableCars);
        });
        Then("^The car \"([^\"]*)\" is available$", (String idCar) ->
                assertThat(
                        testContext.getAvailableCars().stream()
                                .map(AvailableCar::getIdCar)
                                .anyMatch(id -> UUID.fromString(idCar).equals(id))
                ).isTrue()
        );
        When("^I book the car \"([^\"]*)\" between \"([^\"]*)\" and \"([^\"]*)\"$", (String idCar, String startDate, String endDate) -> {
            var bookingPeriod = Period.builder()
                    .startDateTime(toZonedDateTime(startDate))
                    .endDateTime(toZonedDateTime(endDate))
                    .build();
            var bookedCar = bookingCarManager.book(
                    UUID.fromString(idCar),
                    testContext.getCustomer().get().getId(),
                    bookingPeriod
            );
            testContext.setBookedCar(bookedCar);
            testContext.setBookingPeriod(bookingPeriod);
        });
        Then("^This car is booked for me$", () -> {
            var bookedCars = storeBookedCar.getAll(testContext.getBookingPeriod());
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
