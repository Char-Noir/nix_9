package ua.com.alevel.util;

import ua.com.alevel.enums.DateComponent;
import ua.com.alevel.enums.DateFormatEnum;
import ua.com.alevel.enums.MonthsEnum;

import java.util.HashMap;
import java.util.Map;

public class DateSetUtil {
    public static boolean checkDateMap(Map<DateComponent, Long> map) {
        long year = map.get(DateComponent.YEAR);
        MonthsEnum month = MonthsEnum.getMonthByOrder(map.get(DateComponent.MONTH));
        long day = map.get(DateComponent.DAY);
        if (month == MonthsEnum.FEBRUARY) {
            if (DateUtil.isYearLeap(year)) {
                if (day > month.getNumberOfDays()[1]) {
                    throw new RuntimeException("Day in month need to be less or equal to max days of month");
                }
            } else {
                if (day > month.getNumberOfDays()[0]) {
                    throw new RuntimeException("Day in month need to be less or equal to max days of month");
                }
            }
        } else {
            if (day > month.getNumberOfDays()[0]) {
                throw new RuntimeException("Day in month need to be less or equal to max days of month");
            }
        }

        return true;
    }

    public static Map<DateComponent, Long> getMapFromValues(long year, long month, long day, long hour, long minute, long second, long millisecond) {
        Map<DateComponent, Long> map = new HashMap<>();
        map.put(DateComponent.YEAR, year);
        map.put(DateComponent.MONTH, month);
        map.put(DateComponent.DAY, day);
        map.put(DateComponent.HOUR, hour);
        map.put(DateComponent.MINUTE, minute);
        map.put(DateComponent.SECOND, second);
        map.put(DateComponent.MILLISECOND, millisecond);
        return map;
    }

    public static long dateToMillis(Map<DateComponent, Long> map) {
        long millisecond = 0L;
        millisecond += map.get(DateComponent.MILLISECOND);
        millisecond += simplCompToMillis(map.get(DateComponent.SECOND), DateFormatEnum.SECONDS);
        millisecond += simplCompToMillis(map.get(DateComponent.MINUTE), DateFormatEnum.MINUTES);
        millisecond += simplCompToMillis(map.get(DateComponent.HOUR), DateFormatEnum.HOURS);
        millisecond += simplCompToMillis(map.get(DateComponent.DAY), DateFormatEnum.DAYS);
        millisecond += monthToMillis(map.get(DateComponent.MONTH), map.get(DateComponent.YEAR));
        millisecond += yearsToMillis(map.get(DateComponent.YEAR));

        return millisecond;
    }

    private static long simplCompToMillis(long comp, DateFormatEnum format) {
        return (comp == 0) ? 0 : (comp) * format.getMilliseconds();
    }

    private static long monthToMillis(long month, long year) {
        boolean isYearLeap = DateUtil.isYearLeap(year);
        long millisecond = 0;
        month--;
        while (month != 0) {
            MonthsEnum monthsEnum = MonthsEnum.getMonthByOrder(month);
            if (monthsEnum != MonthsEnum.FEBRUARY || !isYearLeap) {
                millisecond += monthsEnum.getNumberOfDays()[0] * DateFormatEnum.DAYS.getMilliseconds();
            } else {
                millisecond += (monthsEnum.getNumberOfDays()[1]) * DateFormatEnum.DAYS.getMilliseconds();
            }
            month--;
        }
        return millisecond;
    }

    private static long yearsToMillis(long year) {
        long millisecond = 0;
        year--;
        long leapYears = DateUtil.countLeapYears(year);
        long lastYears = year - leapYears;
        millisecond += (leapYears * 366 + lastYears * 365) * DateFormatEnum.DAYS.getMilliseconds();
        return millisecond;
    }

    public static Map<DateComponent, Long> millisToDate(long millisecond) {
        Map<DateComponent, Long> map = getMapFromValues(0, 1, 1, 0, 0, 0, 0);
        millisecond -= millisToYears(millisecond, map);
        millisecond -= millisToMonth(millisecond, map);
        millisecond -= millisToSimplComp(millisecond, map, DateFormatEnum.DAYS, DateComponent.DAY);
        millisecond -= millisToSimplComp(millisecond, map, DateFormatEnum.HOURS, DateComponent.HOUR);
        millisecond -= millisToSimplComp(millisecond, map, DateFormatEnum.MINUTES, DateComponent.MINUTE);
        millisecond -= millisToSimplComp(millisecond, map, DateFormatEnum.SECONDS, DateComponent.SECOND);
        millisecond -= millisToSimplComp(millisecond, map, DateFormatEnum.MILLISECONDS, DateComponent.MILLISECOND);
        if (millisecond != 0) {
            throw new RuntimeException("Coud not convert milliseconds to date");
        }
        checkDateMap(map);
        return map;
    }

    private static long millisToSimplComp(long millisecond, Map<DateComponent, Long> map, DateFormatEnum days, DateComponent day) {
        long comp = millisecond / days.getMilliseconds();
        map.put(day, comp);
        return comp * days.getMilliseconds();
    }

    private static long millisToMonth(long millisecond, Map<DateComponent, Long> map) {
        long days = millisecond / DateFormatEnum.DAYS.getMilliseconds();
        long daysStat = days;
        if (days <= MonthsEnum.JANUARY.getNumberOfDays()[0]) {
            return 0;
        }
        days -= MonthsEnum.JANUARY.getNumberOfDays()[0];
        long month;
        long year = map.get(DateComponent.YEAR);
        if (DateUtil.isYearLeap(year)) {
            if (days <= MonthsEnum.FEBRUARY.getNumberOfDays()[1]) {
                map.put(DateComponent.MONTH, 2L);
                return MonthsEnum.JANUARY.getNumberOfDays()[0] * DateFormatEnum.DAYS.getMilliseconds();
            }
        } else {
            if (days <= MonthsEnum.FEBRUARY.getNumberOfDays()[0]) {
                map.put(DateComponent.MONTH, 2L);
                return MonthsEnum.JANUARY.getNumberOfDays()[0] * DateFormatEnum.DAYS.getMilliseconds();
            }
        }
        days -= (DateUtil.isYearLeap(year)) ? (MonthsEnum.FEBRUARY.getNumberOfDays()[1]) : (MonthsEnum.FEBRUARY.getNumberOfDays()[0]);
        for (month = 3; month <= 12; month++) {
            if (days >= MonthsEnum.getMonthByOrder(month).getNumberOfDays()[0]) {
                days -= MonthsEnum.getMonthByOrder(month).getNumberOfDays()[0];
                continue;
            }
            map.put(DateComponent.MONTH, month);
            return (daysStat - days) * DateFormatEnum.DAYS.getMilliseconds();
        }
        throw new RuntimeException("Wrong month expected.");
    }

    private static long millisToYears(long millisecond, Map<DateComponent, Long> map) {
        if (millisecond < DateFormatEnum.DAYS.getMilliseconds() * 365) {//Year 0 is leap year (ISO 8601)
            return 0;
        }
        long techYears = millisecond / (365 * DateFormatEnum.DAYS.getMilliseconds());
        long techLeap = DateUtil.countLeapYears(techYears);
        if (millisecond >= (techYears * 365 + techLeap) * DateFormatEnum.DAYS.getMilliseconds()) {
            map.put(DateComponent.YEAR, techYears + 1);
            return (techYears * 365 + techLeap) * DateFormatEnum.DAYS.getMilliseconds();
        }
        techYears--;
        map.put(DateComponent.YEAR, techYears + 1);
        return (techYears * 365 + DateUtil.countLeapYears(techYears)) * DateFormatEnum.DAYS.getMilliseconds();
    }

    public static Long getComponent(Map<DateComponent, Long> mapper, DateFormatEnum type) {
        return 0L;
    }
}
