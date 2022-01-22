package ua.com.alevel.hw_8_web_jdbc.persistence.entity;

public enum Category {

    First("Первая", "first"),
    Second("Вторая", "second"),
    High("Высшая", "high");
    private final String value;
    private final String mysqlValue;

    Category(String value, String mysqlValue) {
        this.value = value;
        this.mysqlValue = mysqlValue;
    }

    public static Category getCategoryByString(String value) {
        for (Category category : values()) {
            if (value.equals(category.getValue())) {
                return category;
            }
        }
        throw new IllegalArgumentException("No such category");
    }

    public static Category getCategoryByMysql(String value) {
        for (Category category : values()) {
            if (value.equals(category.getMysqlValue())) {
                return category;
            }
        }
        throw new IllegalArgumentException("No such category");
    }

    public String getMysqlValue() {
        return mysqlValue;
    }

    public String getValue() {
        return value;
    }
}
