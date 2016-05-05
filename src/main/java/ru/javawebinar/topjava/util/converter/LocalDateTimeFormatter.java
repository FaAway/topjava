package ru.javawebinar.topjava.util.converter;

import org.springframework.format.Formatter;
import ru.javawebinar.topjava.util.TimeUtil;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Locale;

/**
 * GKislin
 * 15.04.2015.
 */
public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {

    @Override
    public LocalDateTime parse(String text, Locale locale) throws ParseException {
        return TimeUtil.parseLocalDateTime(text);
    }

    @Override
    public String print(LocalDateTime lt, Locale locale) {
        return lt.format(TimeUtil.DATE_TIME_FORMATTER);
    }
}
