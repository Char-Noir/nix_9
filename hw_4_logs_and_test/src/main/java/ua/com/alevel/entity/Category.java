package ua.com.alevel.entity;

public enum Category {

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

    public String getValue() {
        return value;
    }
}
