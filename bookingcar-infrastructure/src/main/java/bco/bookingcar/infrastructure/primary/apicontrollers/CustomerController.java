package bco.bookingcar.infrastructure.primary.apicontrollers;

import bco.bookingcar.application.CustomerManager;
import bco.bookingcar.exceptions.BookingCarException;
import bco.bookingcar.infrastructure.primary.resources.CustomerResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/customers")
@Tag(name = "Customers", description = "Operations available on Customers")
@Slf4j
public class CustomerController {
    private final CustomerManager customerManager;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(tags = "Customers", summary = "Read a customer")
    public ResponseEntity<CustomerResource> readAbonne(@PathVariable String id) throws BookingCarException {
        return ResponseEntity.ok(
                CustomerResource.fromDomain(
                        customerManager.findById(UUID.fromString(id))
                )
        );
    }

    @ExceptionHandler(BookingCarException.class)
    private void handleCustomerNotFoundException(HttpServletResponse response, BookingCarException exception) throws IOException {
        log.error("The customer does not exist", exception.getMessage());
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());
    }
}
