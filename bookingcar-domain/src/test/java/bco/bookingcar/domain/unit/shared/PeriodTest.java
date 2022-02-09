package bco.bookingcar.domain.unit.shared;

import bco.bookingcar.domain.shared.Period;
import bco.bookingcar.domain.shared.StartDateAfterEndDateException;
import bco.bookingcar.exceptions.MissingMandatoryValueException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("Period value object")
public class PeriodTest {

    @Nested
    @DisplayName("invariant tests")
    class InvariantTest {

        @Test
        @DisplayName("Check valid object")
        void check_valid_object() {
            assertThatCode(PeriodFactory::build).doesNotThrowAnyException();
        }

        @Test
        @DisplayName("Start date time must not be null")
        void start_date_must_not_be_null() {
            assertThatThrownBy(() -> PeriodFactory.build().withStartDateTime(null)).isInstanceOf(MissingMandatoryValueException.class);
        }

        @Test
        @DisplayName("End date time must not be null")
        void end_date_must_not_be_null() {
            assertThatThrownBy(() -> PeriodFactory.build().withEndDateTime(null)).isInstanceOf(MissingMandatoryValueException.class);
        }

        @Test
        @DisplayName("End date time must be after start date")
        void end_date_must_be_after_start_date() {
            var date = ZonedDateTime.now();
            assertThatThrownBy(() -> PeriodFactory.build().withStartDateTime(date).withEndDateTime(date)).isInstanceOf(StartDateAfterEndDateException.class);
        }
    }

    @Nested
    @DisplayName("has intersection")
    class HasIntersectionTest {
        @Test
        void has_intersection_is_false_if_start_date_1_is_after_end_date_2() {
            var period1 = PeriodFactory.build();
            var period2 = Period.builder()
                    .startDateTime(period1.getEndDateTime().plusDays(1L))
                    .endDateTime(period1.getEndDateTime().plusDays(2L))
                    .build();

            assertThat(period1.hasIntersectionWith(period2)).isFalse();
        }

        @Test
        void has_intersection_is_false_if_end_date_1_is_before_end_date_2() {
            var period1 = PeriodFactory.build();
            var period2 = Period.builder()
                    .startDateTime(period1.getStartDateTime().minusDays(2L))
                    .endDateTime(period1.getStartDateTime().minusDays(1L))
                    .build();

            assertThat(period1.hasIntersectionWith(period2)).isFalse();
        }

        @Test
        void has_intersection_is_true_if_start_date_1_is_equals_end_date_2_and_end_date_1_is_equals_end_date_2() {
            var period1 = PeriodFactory.build();

            assertThat(period1.hasIntersectionWith(period1)).isTrue();
        }

        @Test
        void has_intersection_is_true_if_start_date_1_is_before_start_date_2_and_end_date_1_is_after_start_date_2() {
            var period1 = PeriodFactory.build();
            var period2 = Period.builder()
                    .startDateTime(period1.getStartDateTime().minusHours(1L))
                    .endDateTime(period1.getStartDateTime().plusHours(1L))
                    .build();

            assertThat(period1.hasIntersectionWith(period2)).isTrue();
        }

        @Test
        void has_intersection_is_true_if_start_date_1_is_before_end_date_2_and_end_date_1_is_after_end_date_2() {
            var period1 = PeriodFactory.build();
            var period2 = Period.builder()
                    .startDateTime(period1.getEndDateTime().minusHours(1L))
                    .endDateTime(period1.getEndDateTime().plusHours(1L))
                    .build();

            assertThat(period1.hasIntersectionWith(period2)).isTrue();
        }
    }

    @Nested
    @DisplayName("next day")
    class NextDayTest {
        @Test
        void next_day_add_one_day_to_start_and_end_date() {
            var period = PeriodFactory.build();

            var nextPeriod = period.nextDay();

            assertThat(nextPeriod.getStartDateTime()).isEqualTo(period.getStartDateTime().plusDays(1L));
            assertThat(nextPeriod.getEndDateTime()).isEqualTo(period.getEndDateTime().plusDays(1L));
        }
    }

    @Nested
    @DisplayName("previous day")
    class PreviousDayTest {
        @Test
        void previous_day_remove_one_day_to_start_and_end_date() {
            var period = PeriodFactory.build();

            var nextPeriod = period.previousDay();

            assertThat(nextPeriod.getStartDateTime()).isEqualTo(period.getStartDateTime().minusDays(1L));
            assertThat(nextPeriod.getEndDateTime()).isEqualTo(period.getEndDateTime().minusDays(1L));
        }
    }
}

