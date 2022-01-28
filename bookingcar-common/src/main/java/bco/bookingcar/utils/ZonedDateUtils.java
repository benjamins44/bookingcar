package bco.bookingcar.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZonedDateUtils {
    private final static DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    private final static ZoneId timeZone = ZoneId.of("Z");

    public static ZonedDateTime toZonedDateTime(String strDate) {
        return LocalDateTime.parse(strDate, formatter).atZone(timeZone);
    }

    public static String toString(ZonedDateTime date) {
        return formatter.format(date.toLocalDateTime().atZone(timeZone));
    }
}
