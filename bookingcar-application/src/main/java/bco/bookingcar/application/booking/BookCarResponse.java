package bco.bookingcar.application.booking;

import bco.bookingcar.annotation.DTO;
import bco.bookingcar.domain.booking.BookedCar;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@DTO
public class BookCarResponse {
    private BookedCar bookedCar;
}
