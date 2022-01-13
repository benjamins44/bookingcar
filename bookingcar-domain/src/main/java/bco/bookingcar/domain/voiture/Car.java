package bco.bookingcar.domain.voiture;

import bco.bookingcar.annotation.AggregateRoot;
import lombok.*;

import java.util.UUID;

import static bco.bookingcar.validation.Validate.isPositiveAndNotNull;
import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

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

    public Car(UUID id, String brand, String model, Integer numberOfPlace, CarCategory category) {
        notEmpty(brand, "The brand is mandatory");
        notEmpty(model, "The model is mandatory");
        isPositiveAndNotNull(numberOfPlace, "The number of place must be positive and is mandatory");
        notNull(category, " The category is mandatory");

        this.id = id;
        this.brand = brand;
        this.model = model;
        this.numberOfPlace = numberOfPlace;
        this.category = category;
    }
}
