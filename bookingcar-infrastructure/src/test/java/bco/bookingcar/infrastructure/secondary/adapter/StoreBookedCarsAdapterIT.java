package bco.bookingcar.infrastructure.secondary.adapter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import bco.bookingcar.domain.shared.Period;
import bco.bookingcar.domain.unit.booking.BookedCarFactory;
import bco.bookingcar.domain.unit.car.CarFactory;
import bco.bookingcar.infrastructure.secondary.configuration.PostgresqlContainerConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@Import(PostgresqlContainerConfiguration.class)
public class StoreBookedCarsAdapterIT {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final ZoneId timeZone = ZoneId.systemDefault();
    private final static UUID carExisting = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

    @Autowired
    private StoreBookedCarsAdapter storeBookedCarsAdapter;

    @Nested
    @DisplayName("Test add booked car persistence")
    class AddBookedCar {
        @Test
        @DisplayName("Add booked car works")
        void add_customer_work() {
            var bookedCar = storeBookedCarsAdapter.add(BookedCarFactory.build());

            assertThat(bookedCar.getId()).isNotNull();
        }
    }

    @Nested
    @SpringBootTest
    @Import(PostgresqlContainerConfiguration.class)
    @Sql(scripts = { "classpath:IT_datas.sql" }, executionPhase = BEFORE_TEST_METHOD)
    @DisplayName("Test get all by period persistence")
    class GetAllByPeriodTest {
        @Test
        @DisplayName("Get all by period works with one booked")
        void get_all_by_period_work_with_one_booked() {
            var period = Period.builder()
                    .startDateTime(toZonedDateTime("2016-06-22 18:10"))
                    .endDateTime(toZonedDateTime("2016-06-22 19:00"))
                    .build();
            var bookedCars = storeBookedCarsAdapter.getAll(period);

            assertThat(bookedCars.size()).isEqualTo(1);
        }

        @Test
        @DisplayName("Get all by period works with two booked")
        void get_all_by_period_work_with_two_booked() {
            var period = Period.builder()
                    .startDateTime(toZonedDateTime("2016-06-22 22:10"))
                    .endDateTime(toZonedDateTime("2016-06-23 23:59"))
                    .build();
            var bookedCars = storeBookedCarsAdapter.getAll(period);

            assertThat(bookedCars.size()).isEqualTo(3);
        }

        @Test
        @DisplayName("Get all by period works with none booked")
        void get_all_by_period_work_with_none_booked() {
            var period = Period.builder()
                    .startDateTime(toZonedDateTime("2016-05-22 22:10"))
                    .endDateTime(toZonedDateTime("2016-05-23 23:59"))
                    .build();
            var bookedCars = storeBookedCarsAdapter.getAll(period);

            assertThat(bookedCars.size()).isZero();
        }
    }

    @Nested
    @SpringBootTest
    @Import(PostgresqlContainerConfiguration.class)
    @Sql(scripts = { "classpath:IT_datas.sql" }, executionPhase = BEFORE_TEST_METHOD)
    @DisplayName("Test get all by period and id car persistence")
    class GetAllByPeriodTestAndIdCar {
        @Test
        @DisplayName("Get all by period and id car works with one booked")
        void get_all_by_period_and_id_car_work_with_one_booked() {
            var period = Period.builder()
                    .startDateTime(toZonedDateTime("2016-06-22 18:10"))
                    .endDateTime(toZonedDateTime("2016-06-22 19:00"))
                    .build();
            var bookedCars = storeBookedCarsAdapter.getBookedCarByCarAndPeriod(CarFactory.build().withId(carExisting), period);

            assertThat(bookedCars.size()).isEqualTo(1);
        }

        @Test
        @DisplayName("Get all by period and id car works with two booked")
        void get_all_by_period_id_car_work_with_two_booked() {
            var period = Period.builder()
                    .startDateTime(toZonedDateTime("2016-06-22 22:10"))
                    .endDateTime(toZonedDateTime("2016-06-23 23:59"))
                    .build();
            var bookedCars = storeBookedCarsAdapter.getBookedCarByCarAndPeriod(CarFactory.build().withId(carExisting), period);

            assertThat(bookedCars.size()).isEqualTo(2);
        }

        @Test
        @DisplayName("Get all by period and id car works with none booked")
        void get_all_by_period_id_car_work_with_none_booked() {
            var period = Period.builder()
                    .startDateTime(toZonedDateTime("2016-05-22 22:10"))
                    .endDateTime(toZonedDateTime("2016-05-23 23:59"))
                    .build();
            var bookedCars = storeBookedCarsAdapter.getBookedCarByCarAndPeriod(CarFactory.build().withId(carExisting), period);

            assertThat(bookedCars.size()).isZero();
        }
    }

    private ZonedDateTime toZonedDateTime(String strDate) {
        return LocalDateTime.parse(strDate, formatter).atZone(timeZone);
    }
}
