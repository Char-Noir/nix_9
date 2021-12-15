package ua.com.alevel.enums;

public enum DateComponent {
    YEAR(0, 9999, 0, "y"),
    MONTH(1, 12, 1, "mo"),
    DAY(1, 31, 1, "d"),
    HOUR(0, 23, 0, "h"),
    MINUTE(0, 59, 0, "mi"),
    SECOND(0, 59, 0, "s"),
    MILLISECOND(0, 999, 0, "ml");

    private final long minValue;
    private final long maxValue;
    private final long defaultValue;
    private final String shortName;

    DateComponent(long minValue, long maxValue, long defaultValue, String shortName) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.defaultValue = defaultValue;
        this.shortName = shortName;
    }

    public static DateComponent getComponentByName(String component) {
        for (DateComponent comp : DateComponent.values()) {
            if (comp.name().toLowerCase().equals(component) || comp.getShortName().toLowerCase().equals(component)) {
                return comp;
            }
        }
        throw new IllegalArgumentException("No such DateComponent");
    }

    @Override
    public String toString() {
        return name() + " {" +
                "minValue=" + minValue +
                ", maxValue=" + maxValue +
                ", shortName='" + shortName + '\'' +
                '}';
    }

    public long getMinValue() {
        return minValue;
    }

    public long getMaxValue() {
        return maxValue;
    }

    public long getDefaultValue() {
        return defaultValue;
    }

    public String getShortName() {
        return shortName;
    }
}
