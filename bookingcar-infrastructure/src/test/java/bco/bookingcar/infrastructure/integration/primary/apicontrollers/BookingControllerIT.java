package bco.bookingcar.infrastructure.integration.primary.apicontrollers;

import bco.bookingcar.application.BookCarUseCase;
import bco.bookingcar.application.SearchAvailableCarsUseCase;
import bco.bookingcar.application.booking.AvailableCar;
import bco.bookingcar.application.booking.SearchAvailableCarsRequest;
import bco.bookingcar.application.unit.booking.AvailableCarFactory;
import bco.bookingcar.application.unit.booking.SearchAvailableCarsCriteriasFactory;
import bco.bookingcar.domain.booking.BookedCar;
import bco.bookingcar.domain.booking.CarNotAvailableException;
import bco.bookingcar.domain.unit.shared.PeriodFactory;
import bco.bookingcar.exceptions.BusinessException;
import bco.bookingcar.infrastructure.integration.primary.configuration.ApplicationConfigurationTest;
import bco.bookingcar.infrastructure.integration.primary.stubs.BookCarUseCaseStub;
import bco.bookingcar.infrastructure.integration.primary.stubs.SearchAvailableCarsUseCaseStub;
import bco.bookingcar.utils.ZonedDateUtils;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({ApplicationConfigurationTest.class})
@WebMvcTest
public class BookingControllerIT {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private BookCarUseCase bookCarUseCase;

    @Autowired
    private SearchAvailableCarsUseCase searchAvailableCarsUseCase;

    @BeforeEach
    void setup() {
        ((BookCarUseCaseStub) bookCarUseCase).reset();
        ((SearchAvailableCarsUseCaseStub) searchAvailableCarsUseCase).reset();
    }

    @Test
    void can_search_available_car_with_empty_result() throws Exception {
        var search = SearchAvailableCarsCriteriasFactory.build();

        performSearchAvailableCars(search)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", hasSize(0)));
    }


    @Test
    void can_search_available_car_with_one_result() throws Exception {
        var search = SearchAvailableCarsCriteriasFactory.build();
        var availableCar = AvailableCarFactory.build();
        this.setAvailableCar(List.of(
                availableCar,
                availableCar
        ));

        performSearchAvailableCars(search)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", hasSize(2)))
                .andExpect(jsonPath("$.result[0].car.brand", is(availableCar.getCar().getBrand())));
    }

    @Test
    void search_has_self_link() throws Exception {
        var search = SearchAvailableCarsCriteriasFactory.build();
        var linkCurrentPeriod = String.format(
                "/booking?startDateTime=%s&endDateTime=%s&customerId=%s",
                ZonedDateUtils.toString(search.getPeriod().getStartDateTime()),
                ZonedDateUtils.toString(search.getPeriod().getEndDateTime()),
                search.getCustomerId().toString()
        );

        performSearchAvailableCars(search)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links.self.href", containsString(linkCurrentPeriod)));
    }

    @Test
    void search_has_next_link_with_each_date_plus_one_day() throws Exception {
        var search = SearchAvailableCarsCriteriasFactory.build();
        var linkNextDayPeriod = String.format(
                "/booking?startDateTime=%s&endDateTime=%s&customerId=%s",
                ZonedDateUtils.toString(search.getPeriod().getStartDateTime().plusDays(1L)),
                ZonedDateUtils.toString(search.getPeriod().getEndDateTime().plusDays(1L)),
                search.getCustomerId().toString()
        );

        performSearchAvailableCars(search)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links.nextDay.href", containsString(linkNextDayPeriod)));
    }

    @Test
    void search_has_previous_link_with_each_date_minus_one_day() throws Exception {
        var search = SearchAvailableCarsCriteriasFactory.build();
        var linkPreviousDayPeriod = String.format(
                "/booking?startDateTime=%s&endDateTime=%s&customerId=%s",
                ZonedDateUtils.toString(search.getPeriod().getStartDateTime().minusDays(1L)),
                ZonedDateUtils.toString(search.getPeriod().getEndDateTime().minusDays(1L)),
                search.getCustomerId().toString()
        );

        performSearchAvailableCars(search)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links.previousDay.href", containsString(linkPreviousDayPeriod)));
    }

    @Test
    void search_has_link_to_book() throws Exception {
        var search = SearchAvailableCarsCriteriasFactory.build();
        var availableCar = AvailableCarFactory.build();
        this.setAvailableCar(List.of(
                availableCar,
                availableCar
        ));

        var linkToBook = String.format(
                "/booking/car/%s/customer/%s/startDateTime/%s/endDateTime/%s",
                availableCar.getCar().getId().toString(),
                search.getCustomerId().toString(),
                ZonedDateUtils.toString(availableCar.getPeriod().getStartDateTime()),
                ZonedDateUtils.toString(availableCar.getPeriod().getEndDateTime())
        );

        performSearchAvailableCars(search)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result[0]._links.book.href", containsString(linkToBook)));
    }

    @NotNull
    private ResultActions performSearchAvailableCars(SearchAvailableCarsRequest search) throws Exception {
        return this.mvc.perform(
                get(String.format("/booking?startDateTime=%s&endDateTime=%s&customerId=%s", ZonedDateUtils.toString(search.getPeriod().getStartDateTime()),
                        ZonedDateUtils.toString(search.getPeriod().getEndDateTime()), search.getCustomerId())).accept(APPLICATION_JSON)
                        .contentType(APPLICATION_JSON));
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
        ((BookCarUseCaseStub) bookCarUseCase).setBookedCar(bookedCar);
    }

    private void setAvailableCar(List<AvailableCar> availableCars) {
        ((SearchAvailableCarsUseCaseStub) searchAvailableCarsUseCase).setAvailableCars(availableCars);
    }

    private void setBookingCarException(BusinessException exception) {
        ((BookCarUseCaseStub) bookCarUseCase).setException(exception);
    }
}
