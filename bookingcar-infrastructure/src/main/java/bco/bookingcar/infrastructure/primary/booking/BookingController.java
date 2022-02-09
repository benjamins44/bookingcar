package bco.bookingcar.infrastructure.primary.booking;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import bco.bookingcar.application.BookCarUseCase;
import bco.bookingcar.application.SearchAvailableCarsUseCase;
import bco.bookingcar.application.booking.BookCarPresenter;
import bco.bookingcar.application.booking.BookCarRequest;
import bco.bookingcar.application.booking.SearchAvailableCarsPresenter;
import bco.bookingcar.application.booking.SearchAvailableCarsRequest;
import bco.bookingcar.exceptions.BusinessException;
import bco.bookingcar.infrastructure.primary.shared.PeriodResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/booking")
@Tag(name = "Booking", description = "Operations available on Booking")
public class BookingController {
    private final BookCarUseCase bookCarUseCase;
    private final BookCarPresenter<BookedCarResource> bookCarPresenter;
    private final SearchAvailableCarsUseCase searchAvailableCarsUseCase;
    private final SearchAvailableCarsPresenter<SearchAvailableCarsResultResource> searchAvailableCarsPresenter;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(tags = "Booking", summary = "Get all available cars on a period")
    public ResponseEntity<SearchAvailableCarsResultResource> getAllByPeriod(
            @Schema(description = "Start date and time of the period") @RequestParam String startDateTime,
            @Schema(description = "End date and time of the period") @RequestParam String endDateTime,
            @Schema(description = "Customer id that book a car") @RequestParam String customerId) {
        searchAvailableCarsUseCase.execute(
                SearchAvailableCarsRequest.builder()
                        .customerId(UUID.fromString(customerId))
                        .period(new PeriodResource(startDateTime, endDateTime).toDomain())
                        .build(),
                searchAvailableCarsPresenter
        );
        return ResponseEntity.ok(searchAvailableCarsPresenter.viewModel());
    }

    @PostMapping("/car/{idCar}/customer/{idCustomer}/startDateTime/{startDateTime}/endDateTime/{endDateTime}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(tags = "Booking", summary = "Book a car for a customer on a period")
    public ResponseEntity<BookedCarResource> book(@Schema(description = "Id car to book") @PathVariable String idCar,
            @Schema(description = "Id customer who books") @PathVariable String idCustomer,
            @Schema(description = "Start date and time of the period") @PathVariable String startDateTime,
            @Schema(description = "End date and time of the period") @PathVariable String endDateTime) throws BusinessException {
        var period = PeriodResource.builder().startDateTime(startDateTime).endDateTime(endDateTime).build();
        bookCarUseCase.execute(
                BookCarRequest.builder()
                        .carId(UUID.fromString(idCar))
                        .customerId(UUID.fromString(idCustomer))
                        .period(period.toDomain())
                        .build(),
                bookCarPresenter
        );

        var bookedCar = bookCarPresenter.viewModel();
        final URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path(String.format("/%s", bookedCar.getId())).build().toUri();
        return ResponseEntity.created(location).body(bookedCar);
    }
}
