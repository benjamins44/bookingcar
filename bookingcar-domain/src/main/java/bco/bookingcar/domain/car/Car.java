package bco.bookingcar.domain.car;

import bco.bookingcar.annotation.AggregateRoot;
import bco.bookingcar.exceptions.BusinessException;
import lombok.*;

import java.util.UUID;

import static bco.bookingcar.validation.Assert.field;

@With
@Builder
@Getter
@ToString
@EqualsAndHashCode(of = {"id"})
@AggregateRoot
public class Car {
    private UUID id;
    private String brand;
    private String model;
    private Integer numberOfPlace;
    private CarCategory category;

    @SneakyThrows({BusinessException.class})
    public Car(UUID id, String brand, String model, Integer numberOfPlace, CarCategory category) {
        field("brand", brand).notBlank();
        field("model", model).notBlank();
        field("numberOfPlace", numberOfPlace).notNull().isPositive();
        field("category", category).notNull();

        this.id = id;
        this.brand = brand;
        this.model = model;
        this.numberOfPlace = numberOfPlace;
        this.category = category;
    }
}
