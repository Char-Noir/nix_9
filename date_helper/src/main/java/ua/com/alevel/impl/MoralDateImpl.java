package ua.com.alevel.impl;

import ua.com.alevel.MoralDate;
import ua.com.alevel.enums.DateComponent;
import ua.com.alevel.enums.DateFormatEnum;
import ua.com.alevel.enums.MonthsEnum;
import ua.com.alevel.util.DateSetUtil;
import ua.com.alevel.util.DateUtil;
import ua.com.alevel.util.StringParser;

import java.util.HashMap;
import java.util.Map;

import static ua.com.alevel.enums.DateComponent.*;

public class MoralDateImpl implements MoralDate {

    private final Long milliseconds = 0L;
    private Map<DateComponent, Long> map = new HashMap<>();

    public MoralDateImpl(String s) {
        map = StringParser.parseDate(s);
        DateSetUtil.checkDateMap(map);
    }

    public MoralDateImpl(String s, String format) {
        map = StringParser.parseDate(s, format);
        DateSetUtil.checkDateMap(map);
    }

    public MoralDateImpl() {
        DateComponent[] formats = DateComponent.values();
        for (DateComponent format : formats) {
            map.put(format, format.getDefaultValue());
        }
    }

    MoralDateImpl(Map<DateComponent, Long> inputMap) {
        this();
        for (Map.Entry<DateComponent, Long> row :
                inputMap.entrySet()) {
            DateComponent component = row.getKey();
            long value = row.getValue();
            if (value < component.getMinValue() || value > component.getMaxValue()) {
                throw new IllegalArgumentException(component.name() + " need to be between " + component.getMinValue() + " and " + component.getMaxValue());
            }
            this.map.put(component, value);
        }
        DateSetUtil.checkDateMap(this.map);
    }

    MoralDateImpl(long year, long month, long day, long hour, long minute, long second, long millisecond) {
        this(DateSetUtil.getMapFromValues(year, month, day, hour, minute, second, millisecond));
    }

    public MoralDateImpl(long year, long month, long day) {
        this(year, month, day, DateComponent.HOUR.getDefaultValue(), DateComponent.MINUTE.getDefaultValue(), DateComponent.SECOND.getDefaultValue(), DateComponent.MILLISECOND.getDefaultValue());
    }

    MoralDateImpl(long hour, long minute, long second, long millisecond) {
        this(YEAR.getDefaultValue(), DateComponent.MONTH.getDefaultValue(), DateComponent.DAY.getDefaultValue(), hour, minute, second, millisecond);
    }

    @Override
    public Long diff(MoralDate date1, DateFormatEnum type) {
        switch (type) {
            case YEARS -> {
                return Math.abs(map.get(YEAR) - date1.get(DateFormatEnum.YEARS));
            }
            case MONTHS -> {
                return Math.abs((map.get(MONTH) + 12 * map.get(YEAR)) - (date1.get(DateFormatEnum.MONTHS) + 12 * date1.get(DateFormatEnum.YEARS)));
            }
            default -> {
                return Math.abs((DateSetUtil.dateToMillis(map) - date1.getMillis()) / type.getMilliseconds());
            }
        }
    }

    @Override
    public MoralDate add(DateFormatEnum type, long time, boolean inplace) {
        long m1 = DateSetUtil.dateToMillis(map);
        m1 += time * type.getMilliseconds();
        Map<DateComponent, Long> mapper = DateSetUtil.millisToDate(m1);
        if (inplace) {
            map = mapper;
        }
        return new MoralDateImpl(mapper);
    }

    @Override
    public MoralDate sub(DateFormatEnum type, long time, boolean inplace) {
        long m1 = DateSetUtil.dateToMillis(map);
        m1 = Math.abs(m1 - time * type.getMilliseconds());
        Map<DateComponent, Long> mapper = DateSetUtil.millisToDate(m1);
        if (inplace) {
            map = mapper;
        }
        return new MoralDateImpl(mapper);
    }

    @Override
    public int compareTo(MoralDate moralDate) {
        long m1 = DateSetUtil.dateToMillis(map);
        long m2 = moralDate.getMillis();
        return (int) (m1 - m2);
    }

    @Override
    public long get(DateFormatEnum type) {
        return map.get(DateUtil.getByFormat(type));
    }

    @Override
    public long getMillis() {
        return DateSetUtil.dateToMillis(map);
    }

    public String toString() {
        return "Date: " + map.get(YEAR) + " years, " + MonthsEnum.getMonthByOrder(map.get(DateComponent.MONTH)).name() + " , " + map.get(DateComponent.DAY) + " days, " + map.get(DateComponent.HOUR) + " hours, " + map.get(DateComponent.MINUTE) + " minutes, " + map.get(DateComponent.SECOND) + " seconds,  " + map.get(DateComponent.MILLISECOND) + " milliseconds.\n";
    }

    @Override
    public String toString(String format) {
        return StringParser.toFormatString(map, format);
    }

}
