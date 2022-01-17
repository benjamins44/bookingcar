package bco.bookingcar.infrastructure.secondary.entities;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import bco.bookingcar.annotation.DomainEntity;
import bco.bookingcar.domain.booking.BookedCar;
import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.car.CarCategory;
import bco.bookingcar.domain.shared.Period;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.With;

import static org.apache.commons.lang3.Validate.notNull;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BOOKED_CAR")
public class BookedCarEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false)
    private UUID id;

    @Column(name = "ID_CUSTOMER")
    private UUID idCustomer;

    @Column(name = "ID_CAR")
    private UUID idCar;

    @Column(name = "START_DATE_TIME")
    private ZonedDateTime startDateTime;

    @Column(name = "END_DATE_TIME")
    private ZonedDateTime endDateTime;

    public static BookedCarEntity fromDomain(BookedCar bookedCar) {
        return new BookedCarEntity(
                bookedCar.getId(),
                bookedCar.getIdCustomer(),
                bookedCar.getIdCar(),
                bookedCar.getPeriod().getStartDateTime(),
                bookedCar.getPeriod().getEndDateTime()
        );
    }
    public BookedCar toDomain() {
        return new BookedCar(
                this.getId(),
                this.getIdCustomer(),
                this.getIdCar(),
                Period.builder()
                        .startDateTime(this.getStartDateTime())
                        .endDateTime(this.getEndDateTime())
                        .build()
        );
    }

}
