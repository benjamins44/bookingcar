package bco.bookingcar.infrastructure.primary.resources;

import bco.bookingcar.domain.booking.BookedCar;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(name = "Booked Car", description = "A booked car")
public class BookedCarResource {
    @Schema(description = "Id")
    private UUID id;

    @Schema(description = "Customer Id")
    private UUID idCustomer;

    @Schema(description = "Car id")
    private UUID idCar;

    @Schema(description = "Booking period")
    private PeriodResource period;

    public static BookedCarResource fromDomain(BookedCar bookedCar) {
        return new BookedCarResource(
                bookedCar.getId(),
                bookedCar.getIdCustomer(),
                bookedCar.getIdCar(),
                PeriodResource.fromDomain(bookedCar.getPeriod())
        );
    }

    public BookedCar toDomain() {
        return new BookedCar(
                this.getId(),
                this.getIdCustomer(),
                this.getIdCar(),
                this.getPeriod().toDomain(),
                null
        );
    }
}
