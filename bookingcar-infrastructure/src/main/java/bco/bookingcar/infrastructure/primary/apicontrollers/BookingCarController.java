package bco.bookingcar.infrastructure.primary.apicontrollers;

import bco.bookingcar.application.BookingCarManager;
import bco.bookingcar.exceptions.BookingCarException;
import bco.bookingcar.exceptions.BusinessException;
import bco.bookingcar.infrastructure.primary.resources.AvailableCarResource;
import bco.bookingcar.infrastructure.primary.resources.BookedCarResource;
import bco.bookingcar.infrastructure.primary.resources.PeriodResource;
import bco.bookingcar.infrastructure.primary.resources.SearchAvailableCarsCriteriasResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/booking")
@Tag(name = "Booking", description = "Operations available on Booking")
public class BookingCarController {
    private final BookingCarManager bookingCarManager;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(tags = "Booking", summary = "Get all available cars on a period")
    public ResponseEntity<List<AvailableCarResource>> getAllByPeriod(SearchAvailableCarsCriteriasResource searchAvailableCarsCriteriasResource) {
        return ResponseEntity.ok(
                AvailableCarResource.fromDomain(
                        bookingCarManager.search(searchAvailableCarsCriteriasResource.toDomain())
                )
        );
    }

    @PostMapping("/car/{idCar}/customer/{idCustomer}/startDateTime/{startDateTime}/endDateTime/{endDateTime}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(tags = "Booking", summary = "Book a car for a customer on a period")
    public ResponseEntity<BookedCarResource> book(@PathVariable UUID idCar, @PathVariable UUID idCustomer, PeriodResource period) throws BusinessException {
        var bookedCar = BookedCarResource.fromDomain(
                bookingCarManager.book(idCar, idCustomer, period.toDomain())
        );

        final URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path(String.format("/%s", bookedCar.getId())).build().toUri();
        return ResponseEntity.created(location).body(bookedCar);
    }

    @ExceptionHandler(BookingCarException.class)
    private void handleCarNotFoundException(HttpServletResponse response, BookingCarException exception) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());
    }
}
