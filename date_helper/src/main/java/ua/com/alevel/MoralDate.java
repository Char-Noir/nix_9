package ua.com.alevel;

import ua.com.alevel.enums.DateFormatEnum;

public interface MoralDate extends Comparable<MoralDate> {

    Long diff(MoralDate date1, DateFormatEnum type);

    MoralDate add(DateFormatEnum type, long time, boolean inplace);

    MoralDate sub(DateFormatEnum type, long time, boolean inplace);

    int compareTo(MoralDate moralDate);

    long get(DateFormatEnum type);

    long getMillis();

    String toString();

    String toString(String format);
}
