package bco.bookingcar.infrastructure.integration.secondary.adapter;

import bco.bookingcar.domain.booking.BookedCarCreatedEvent;
import bco.bookingcar.domain.unit.car.CarFactory;
import bco.bookingcar.domain.unit.customer.CustomerFactory;
import bco.bookingcar.domain.unit.shared.PeriodFactory;
import bco.bookingcar.infrastructure.integration.secondary.configuration.PostgresqlContainerConfiguration;
import bco.bookingcar.infrastructure.secondary.adapter.BookingCarEventsDispatcherAdapter;
import cucumber.api.java.en_old.Ac;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(PostgresqlContainerConfiguration.class)
@RecordApplicationEvents
@ActiveProfiles("primary-integration-test")
public class BookingCarEventsDispatcherAdapterIT {

    @Autowired
    private ApplicationEvents applicationEvents;

    @Autowired
    private BookingCarEventsDispatcherAdapter bookingCarEventsDispatcherAdapter;

    @Nested
    @DisplayName("Test send events work")
    class SendEventsTest {
        @Test
        void send_events_work() {
            var myEvent = BookedCarCreatedEvent.builder()
                    .car(CarFactory.build())
                    .customer(CustomerFactory.build())
                    .period(PeriodFactory.build())
                    .build();

            bookingCarEventsDispatcherAdapter.dispatch(List.of(myEvent));

            assertThat(applicationEvents
                    .stream(BookedCarCreatedEvent.class)
                    .filter(event -> event.equals(myEvent))
                    .count()).isOne();
        }
    }
}
