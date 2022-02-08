package bco.bookingcar.infrastructure.primary.car;

import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.car.CarCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(name = "Cars", description = "A beautiful car")
public class CarResource {
    @Schema(description = "Car id", accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @Schema(description = "The brand")
    private String brand;

    @Schema(description = "The model")
    private String model;

    @Schema(description = "Number of place into the car")
    private Integer numberOfPlace;

    @Schema(description = "Category")
    private String category;

    public static CarResource fromDomain(Car car) {
        return new CarResource(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getNumberOfPlace(),
                car.getCategory().name()
        );
    }

    public static List<CarResource> fromDomain(List<Car> cars) {
        return cars.stream().map(CarResource::fromDomain).collect(Collectors.toList());
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
