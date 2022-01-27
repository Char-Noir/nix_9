package ua.com.alevel.hw_10_web_repository.persistence.converter;

import ua.com.alevel.hw_10_web_repository.persistence.entity.Category;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CategoryAttributeConverter implements AttributeConverter<Category, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Category category) {
        if (category == null)
            return null;
        return switch (category) {
            case High -> 10;
            case First -> 8;
            case Second -> 9;
            default -> throw new IllegalArgumentException("Unknown category");
        };
    }

    @Override
    public Category convertToEntityAttribute(Integer integer) {
        if (integer == null)
            return null;
        return switch (integer) {
            case 8 -> Category.First;
            case 9 -> Category.Second;
            case 10 -> Category.High;
            default -> throw new IllegalArgumentException("Unknown category: " + integer);
        };
    }
}
