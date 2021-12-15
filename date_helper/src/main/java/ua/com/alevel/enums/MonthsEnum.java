package ua.com.alevel.enums;

import java.util.Locale;

public enum MonthsEnum {

    JANUARY("Jan", 1, 31),
    FEBRUARY("Feb", 2, new int[]{28, 29}),
    MARCH("Mar", 3, 31),
    APRIL("Apr", 4, 30),
    MAY("May", 5, 31),
    JUNE("Jun", 6, 30),
    JULY("Jul", 7, 31),
    AUGUST("Aug", 8, 31),
    SEPTEMBER("Sep", 9, 30),
    OCTOBER("Oct", 10, 31),
    NOVEMBER("Nov", 11, 30),
    DECEMBER("Dec", 12, 31);

    private final String shortName;
    private final byte order;
    private final int[] numberOfDays;

    MonthsEnum(String shortName, int order, int[] numberOfDays) {
        this.shortName = shortName;
        this.order = (byte) order;
        this.numberOfDays = numberOfDays;
    }

    MonthsEnum(String shortName, int order, int numberOfDays) {
        this(shortName, order, new int[]{numberOfDays});
    }

    public static MonthsEnum getMonthByOrder(long order) {
        for (MonthsEnum month : MonthsEnum.values()) {
            if (month.order == order) {
                return month;
            }
        }
        throw new IllegalArgumentException("No such month");
    }

    public static long getOrderByName(String s) {
        s = s.trim().toLowerCase(Locale.ROOT);
        for (MonthsEnum month : MonthsEnum.values()) {
            if (month.name().toLowerCase(Locale.ROOT).equals(s) || month.getShortName().toLowerCase(Locale.ROOT).equals(s)) {
                return month.getOrder();
            }
        }
        throw new IllegalArgumentException("No such month");
    }

    public String getShortName() {
        return shortName;
    }

    public byte getOrder() {
        return order;
    }

    public int[] getNumberOfDays() {
        return numberOfDays;
    }
}
