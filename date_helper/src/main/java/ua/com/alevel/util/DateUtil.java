package ua.com.alevel.util;

import ua.com.alevel.enums.DateComponent;
import ua.com.alevel.enums.DateFormatEnum;

import static ua.com.alevel.enums.DateComponent.*;

public class DateUtil {
    public static boolean isYearLeap(long year) {
        return ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0)));
    }

    public static long countLeapYears(long year) {
        long counter = 0;
        for (int i = 0; i <= year; i++) {
            if (isYearLeap(i)) {
                counter++;
            }
        }
        return counter;
    }

    public static DateFormatEnum getByComponent(DateComponent dateComponent) {
        switch (dateComponent) {
            case MILLISECOND -> {
                return DateFormatEnum.MILLISECONDS;
            }
            case SECOND -> {
                return DateFormatEnum.SECONDS;
            }
            case MINUTE -> {
                return DateFormatEnum.MINUTES;
            }
            case HOUR -> {
                return DateFormatEnum.HOURS;
            }
            case DAY -> {
                return DateFormatEnum.DAYS;
            }
            case MONTH -> {
                return DateFormatEnum.MONTHS;
            }
            case YEAR -> {
                return DateFormatEnum.YEARS;
            }
            default -> throw new IllegalArgumentException("No such valid Date Format");
        }
    }

    public static DateComponent getByFormat(DateFormatEnum dateFormat) {
        switch (dateFormat) {
            case MILLISECONDS -> {
                return MILLISECOND;
            }
            case SECONDS -> {
                return SECOND;
            }
            case MINUTES -> {
                return MINUTE;
            }
            case HOURS -> {
                return HOUR;
            }
            case DAYS -> {
                return DAY;
            }
            case MONTHS -> {
                return MONTH;
            }
            case YEARS -> {
                return YEAR;
            }
            default -> throw new IllegalArgumentException("Nu such valid Date Component");
        }
    }
}
