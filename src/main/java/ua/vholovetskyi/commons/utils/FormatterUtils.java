package ua.vholovetskyi.commons.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Slf4j
public class FormatterUtils {

    private FormatterUtils() {
    }

    private static final String FULL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    private static final String SHORT_DATE_FORMAT = "yyyy-MM-dd";

    public static SimpleDateFormat getFullDateFormat() {
        return new SimpleDateFormat(FULL_DATE_FORMAT);
    }

    public static SimpleDateFormat getShortDateFormat() {
        return new SimpleDateFormat(SHORT_DATE_FORMAT);
    }

    public static LocalDate getCurrencyDate() {
        return LocalDate.now();
    }

    public static LocalDate parsString(String date) {
        LocalDate parsDate = null;
        try {
            parsDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            log.error(e.getMessage());
        }
        return parsDate;
    }
}
