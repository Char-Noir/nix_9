package ua.com.alevel.entity;

import ua.com.alevel.csv.SerializableEnum;

public enum Category implements SerializableEnum {

    First("Первая"),
    Second("Вторая"),
    High("Высшая");
    private final String value;

    Category(String value) {
        this.value = value;
    }

    public static Category getCategoryByString(String value) {
        for (Category category : values()) {
            if (value.equals(category.getValue())) {
                return category;
            }
        }
        throw new IllegalArgumentException("No such category");
    }



    public SerializableEnum getByString(String str) {
        return getCategoryByString(str);
    }

    @Override
    public String toString() {
        return this.value;
    }

    public String getValue() {
        return value;
    }
}
