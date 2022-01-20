package bco.bookingcar.infrastructure.primary.apicontrollers;

import bco.bookingcar.application.CarManager;
import bco.bookingcar.exceptions.BookingCarException;
import bco.bookingcar.infrastructure.primary.resources.CarResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/cars")
@Tag(name = "Cars", description = "Operations available on Cars")
public class CarController {
    private final CarManager carManager;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(tags = "Cars", summary = "Read a car")
    public ResponseEntity<CarResource> readAbonne(@PathVariable String id) throws BookingCarException {
        return ResponseEntity.ok(
                CarResource.fromDomain(
                        carManager.findById(UUID.fromString(id))
                )
        );
    }

    @ExceptionHandler(BookingCarException.class)
    private void handleBookingCarException(HttpServletResponse response, BookingCarException exception) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());
    }
}
