package bco.bookingcar.infrastructure.secondary.entities;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.car.CarCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CAR")
public class CarEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false)
    private UUID id;

    @Column(name = "BRAND")
    private String brand;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "NUMBER_OF_PLACE")
    private Integer numberOfPlace;

    @Column(name = "CATEGORY")
    private String category;

    public static CarEntity fromDomain(Car car) {
        return new CarEntity(
              car.getId(),
              car.getBrand(),
              car.getModel(),
              car.getNumberOfPlace(),
              car.getCategory().name()
        );
    }
    public static List<CarEntity> fromDomain(List<Car> cars) {
        return cars.stream().map(CarEntity::fromDomain).toList();
    }

    public Car toDomain() {
        return new Car(
            this.getId(),
            this.getBrand(),
            this.getModel(),
            this.getNumberOfPlace(),
            CarCategory.getByValue(this.getCategory())
        );
    }
}
