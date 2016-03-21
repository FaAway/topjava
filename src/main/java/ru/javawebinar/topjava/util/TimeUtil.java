package ru.javawebinar.topjava.util;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * GKislin
 * 07.01.2015.
 */
public class TimeUtil {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final LocalDate MAX_DATE = LocalDate.of(3000, 12, 31);
    public static final LocalDate MIN_DATE = LocalDate.of(0, 1, 1);

    public static <T extends Comparable<? super T>> boolean isBetween(T lt, T startTime, T endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static LocalDate parseLocalDate(String date_str) {
        return StringUtils.isEmpty(date_str) ? null : LocalDate.parse(date_str);
    }

    public static LocalTime parseLocalTime(String time_str) {
        return StringUtils.isEmpty(time_str) ? null : LocalTime.parse(time_str);
    }
}
