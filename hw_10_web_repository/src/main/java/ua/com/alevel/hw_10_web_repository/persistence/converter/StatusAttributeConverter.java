package ua.com.alevel.hw_10_web_repository.persistence.converter;

import ua.com.alevel.hw_10_web_repository.persistence.entity.Status;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StatusAttributeConverter implements AttributeConverter<Status, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Status status) {
        if (status == null)
            return null;
        return switch (status) {
            case Admin -> 6;
            case Deleted -> 7;
            case Doctor -> 5;
            case Patient -> 4;
            default -> throw new IllegalArgumentException("Unknown status");
        };
    }

    @Override
    public Status convertToEntityAttribute(Integer integer) {
        if (integer == null)
            return null;
        return switch (integer) {
            case 6 -> Status.Admin;
            case 7 -> Status.Deleted;
            case 4 -> Status.Patient;
            case 5 -> Status.Doctor;
            default -> throw new IllegalArgumentException("Unknown status: " + integer);
        };
    }
}
