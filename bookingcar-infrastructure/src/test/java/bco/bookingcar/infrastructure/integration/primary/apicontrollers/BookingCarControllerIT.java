package bco.bookingcar.infrastructure.integration.primary.apicontrollers;

import bco.bookingcar.application.BookingCarManager;
import bco.bookingcar.application.booking.AvailableCar;
import bco.bookingcar.application.unit.booking.AvailableCarFactory;
import bco.bookingcar.application.unit.booking.SearchAvailableCarsCriteriasFactory;
import bco.bookingcar.domain.booking.BookedCar;
import bco.bookingcar.domain.booking.CarNotAvailableException;
import bco.bookingcar.domain.unit.shared.PeriodFactory;
import bco.bookingcar.exceptions.BookingCarException;
import bco.bookingcar.infrastructure.integration.primary.configuration.ApplicationConfigurationTest;
import bco.bookingcar.infrastructure.integration.primary.fakes.BookingCarManagerFake;
import bco.bookingcar.infrastructure.primary.apicontrollers.BookingCarController;
import bco.bookingcar.utils.ZonedDateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({ApplicationConfigurationTest.class})
@WebMvcTest(BookingCarController.class)
public class BookingCarControllerIT {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private BookingCarManager bookingCarManager;

    @BeforeEach
    void setup() {
        ((BookingCarManagerFake) bookingCarManager).reset();
    }

    @Test
    void can_search_available_car_with_empty_result() throws Exception {
        var search = SearchAvailableCarsCriteriasFactory.build();

        this.mvc.perform(
                        get(String.format("/booking?startDateTime=%s&endDateTime=%s", ZonedDateUtils.toString(search.getPeriod().getStartDateTime()), ZonedDateUtils.toString(search.getPeriod().getEndDateTime())))
                                .accept(APPLICATION_JSON)
                                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void can_search_available_car_with_one_result() throws Exception {
        var search = SearchAvailableCarsCriteriasFactory.build();
        var availableCar = AvailableCarFactory.build();
        this.setAvailableCar(List.of(
                availableCar,
                availableCar
        ));

        this.mvc.perform(
                        get(String.format("/booking?startDateTime=%s&endDateTime=%s", ZonedDateUtils.toString(search.getPeriod().getStartDateTime()), ZonedDateUtils.toString(search.getPeriod().getEndDateTime())))
                                .accept(APPLICATION_JSON)
                                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].car.brand", is(availableCar.getCar().getBrand())));
    }

    @Test
    void can_booked_a_car() throws Exception {
        var idCar = UUID.randomUUID();
        var idCustomer = UUID.randomUUID();
        var period = PeriodFactory.build();
        var bookedCarResult = BookedCar.builder()
                .id(UUID.randomUUID())
                .idCar(idCar)
                .idCustomer(idCustomer)
                .period(period)
                .build();
        setBookedCar(bookedCarResult);

        this.mvc.perform(
                        post(String.format("/booking/car/%s/customer/%s/startDateTime/%s/endDateTime/%s",
                                idCar,
                                idCustomer,
                                ZonedDateUtils.toString(period.getStartDateTime()),
                                ZonedDateUtils.toString(period.getEndDateTime()))
                        )
                                .accept(APPLICATION_JSON)
                                .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(bookedCarResult.getId().toString())))
                .andExpect(jsonPath("$.idCar", is(idCar.toString())))
                .andExpect(jsonPath("$.idCustomer", is(idCustomer.toString())))
                .andExpect(jsonPath("$.period.startDateTime", is(ZonedDateUtils.toString(period.getStartDateTime()))))
                .andExpect(jsonPath("$.period.endDateTime", is(ZonedDateUtils.toString(period.getEndDateTime()))));
    }

    @Test
    void can_receive_exception_when_booked_a_car() throws Exception {
        var period = PeriodFactory.build();
        setBookingCarException(new CarNotAvailableException(UUID.randomUUID()));

        this.mvc.perform(
                        post(String.format("/booking/car/%s/customer/%s/startDateTime/%s/endDateTime/%s",
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                ZonedDateUtils.toString(period.getStartDateTime()),
                                ZonedDateUtils.toString(period.getEndDateTime()))
                        )
                                .accept(APPLICATION_JSON)
                                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    private void setBookedCar(BookedCar bookedCar) {
        ((BookingCarManagerFake) bookingCarManager).setBookedCar(bookedCar);
    }

    private void setAvailableCar(List<AvailableCar> availableCars) {
        ((BookingCarManagerFake) bookingCarManager).setAvailableCars(availableCars);
    }

    private void setBookingCarException(BookingCarException exception) {
        ((BookingCarManagerFake) bookingCarManager).setException(exception);
    }
}
