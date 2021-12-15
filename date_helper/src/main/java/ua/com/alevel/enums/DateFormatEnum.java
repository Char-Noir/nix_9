package ua.com.alevel.enums;

public enum DateFormatEnum {
    MILLISECONDS(1L, "ms"),
    SECONDS(1_000L, "s"),
    MINUTES(60_000L, "mi"),
    HOURS(3_600_000L, "h"),
    DAYS(86_400_000L, "d"),
    WEEKS(604_800_000L, "w"),
    MONTHS(2_629_746_000L, "mo"),
    YEARS(31_556_952_000L, "y");

    private long milliseconds;
    private String shortName;

    DateFormatEnum(long milliseconds, String shortName) {
        this.milliseconds = milliseconds;
        this.shortName = shortName;
    }

    public static DateFormatEnum getFormatByName(String component) {
        for (DateFormatEnum comp : DateFormatEnum.values()) {
            if (comp.name().toLowerCase().equals(component) || comp.getShortName().toLowerCase().equals(component)) {
                return comp;
            }
        }
        throw new IllegalArgumentException("No such DateFormatEnum");
    }

    @Override
    public String toString() {
        return "DateFormatEnum{ shortName='" + shortName + '\'' +
                '}' + '\n';
    }

    public long getMilliseconds() {
        return milliseconds;
    }

    public DateFormatEnum setMilliseconds(long milliseconds) {
        this.milliseconds = milliseconds;
        return this;
    }

    public String getShortName() {
        return shortName;
    }

    public DateFormatEnum setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }
}
