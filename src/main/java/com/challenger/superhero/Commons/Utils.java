package com.challenger.superhero.Commons;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Utils {
    public static final String ARGENTINA_ZONED_DATETIME = "America/Argentina/Buenos_Aires";
    public static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter DATEMONTHYEAR = DateTimeFormatter.ofPattern("MM/yyyy");
    public static final DateTimeFormatter LOCALDATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DATETIME_H_M = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    public static final DateTimeFormatter HORA_MINUTOS = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter DATETIME_H_M_S = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    public static final DateTimeFormatter LOCALDATE_H_M_S = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DATE_MONTH = DateTimeFormatter.ofPattern("MM");
    public static final DateTimeFormatter DATE_YEAR = DateTimeFormatter.ofPattern("yyyy");
    public static final DateTimeFormatter DATE_DAY = DateTimeFormatter.ofPattern("dd");
    public static final DateTimeFormatter DATE_ANIOMES = DateTimeFormatter.ofPattern("yyyyMM");
    public static final DateTimeFormatter ANIOMES_DATE;

    public Utils() {
    }

    public static LocalDateTime LocalDateTimeNow() {
        return ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires")).toLocalDateTime();
    }

    static {
        ANIOMES_DATE = (new DateTimeFormatterBuilder()).appendPattern("yyyyMM").parseDefaulting(ChronoField.DAY_OF_MONTH, 1L).toFormatter();
    }
}
