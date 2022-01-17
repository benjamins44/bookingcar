package bco.bookingcar.infrastructure.secondary.adapter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import bco.bookingcar.domain.car.Car;
import bco.bookingcar.domain.car.CarCategory;
import bco.bookingcar.domain.unit.car.CarFactory;
import bco.bookingcar.infrastructure.secondary.configuration.PostgresqlContainerConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@Import(PostgresqlContainerConfiguration.class)
public class StoreCarsAdapterTest {

    private static UUID carExisting = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

    @Autowired
    private StoreCarsAdapter storeCarsAdapter;

    @Nested
    @DisplayName("Test add car persistence")
    class AddCarTest {
        @Test
        @DisplayName("Add car persistence works")
        void add_car_work() {
            var car = storeCarsAdapter.add(CarFactory.build());

            assertThat(car.getId()).isNotNull();
        }
    }

    @Nested
    @DisplayName("Test add all cars persistence")
    class AddAllCarTest {
        @Test
        @DisplayName("Add all car persistence works")
        void add_all_cars_work() {
            var numberOfCars = 5;
            var cars = storeCarsAdapter.addAll(CarFactory.buildCars(numberOfCars));

            assertThat(cars.stream().filter(car -> car.getId() != null).collect(Collectors.toList()).size()).isEqualTo(numberOfCars);
        }
    }

    @Nested
    @SpringBootTest
    @Import(PostgresqlContainerConfiguration.class)
    @Sql(scripts = { "classpath:IT_datas.sql" }, executionPhase = BEFORE_TEST_METHOD)
    @DisplayName("Test get all cars persistence")
    class GetAllCarTest {
        @Test
        @DisplayName("Get all car persistence works")
        void get_all_cars_work() {
            var cars = storeCarsAdapter.getAll();

            assertThat(cars.stream().filter(car -> car.getId() != null).collect(Collectors.toList()).size()).isEqualTo(4);
        }
    }

    @Nested
    @SpringBootTest
    @Import(PostgresqlContainerConfiguration.class)
    @Sql(scripts = { "classpath:IT_datas.sql" }, executionPhase = BEFORE_TEST_METHOD)
    @DisplayName("Test save all cars persistence")
    class SaveAllCarTest {
        @Test
        @DisplayName("Get all car persistence works")
        void save_all_cars_work() {
            var cars = storeCarsAdapter.saveAll(
                    List.of(Car.builder()
                        .id(carExisting)
                        .brand("new brand")
                        .model("new model")
                        .numberOfPlace(5)
                        .category(CarCategory.MINIVAN)
                        .build())
            );

            assertThat(cars.get(0).getBrand()).isEqualTo("new brand");
        }
    }

    @Nested
    @SpringBootTest
    @Import(PostgresqlContainerConfiguration.class)
    @Sql(scripts = { "classpath:IT_datas.sql" }, executionPhase = BEFORE_TEST_METHOD)
    @DisplayName("Test get car by id persistence")
    class GetByIdTest {
        @Test
        @DisplayName("Get by id car existing works")
        void get_by_id_existing_work() {
            var car = storeCarsAdapter.getById(carExisting);

            assertThat(car.isPresent()).isTrue();
        }

        @Test
        @DisplayName("Get by id car not existing works")
        void get_by_id_not_existing_work() {
            var car = storeCarsAdapter.getById(UUID.randomUUID());

            assertThat(car.isEmpty()).isTrue();
        }
    }
}
