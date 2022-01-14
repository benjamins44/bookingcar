package bco.bookingcar.domain.unit.shared;

import bco.bookingcar.domain.shared.Period;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DisplayName("Period value object")
public class PeriodTest {

    @Nested
    @DisplayName("invariant tests")
    class InvariantTest {

        @Test
        @DisplayName("Start date time must not be null")
        void start_date_must_not_be_null() {
            assertThatThrownBy(() -> PeriodFactory.build().withStartDateTime(null)).isInstanceOf(NullPointerException.class);
        }

        @Test
        @DisplayName("End date time must not be null")
        void end_date_must_not_be_null() {
            assertThatThrownBy(() -> PeriodFactory.build().withEndDateTime(null)).isInstanceOf(NullPointerException.class);
        }

        @Test
        @DisplayName("End date time must be after start date")
        void end_date_must_be_after_start_date() {
            var date = ZonedDateTime.now();
            assertThatThrownBy(() -> PeriodFactory.build().withStartDateTime(date).withEndDateTime(date)).isInstanceOf(IllegalArgumentException.class);
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
}
