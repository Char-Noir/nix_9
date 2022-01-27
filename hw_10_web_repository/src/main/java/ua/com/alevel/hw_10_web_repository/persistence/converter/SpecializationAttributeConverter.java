package ua.com.alevel.hw_10_web_repository.persistence.converter;

import ua.com.alevel.hw_10_web_repository.persistence.entity.Specialization;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class SpecializationAttributeConverter implements AttributeConverter<Specialization, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Specialization specialization) {
        if (specialization == null)
            return null;
        return switch (specialization) {
            case Therapist -> 32;
            case Local_therapist -> 33;
            case Surgeon -> 34;
            case Ophthalmologist -> 35;
            case Urologist -> 36;
            case Obstetrician_gynecologist -> 37;
            case General_practitioner -> 38;
            case Otorhinolaryngologist -> 39;
            case Dentist_therapist -> 40;
            case Dentist_surgeon -> 41;
            case Pediatrician -> 42;
            case District_pediatrician -> 43;
            case Dentist -> 44;
            default -> throw new IllegalArgumentException("Unknown specialization");
        };
    }

    @Override
    public Specialization convertToEntityAttribute(Integer integer) {
        if (integer == null)
            return null;
        return switch (integer) {
            case 32 -> Specialization.Therapist;
            case 33 -> Specialization.Local_therapist;
            case 34 -> Specialization.Surgeon;
            case 35 -> Specialization.Ophthalmologist;
            case 36 -> Specialization.Urologist;
            case 37 -> Specialization.Obstetrician_gynecologist;
            case 38 -> Specialization.General_practitioner;
            case 39 -> Specialization.Otorhinolaryngologist;
            case 40 -> Specialization.Dentist_therapist;
            case 41 -> Specialization.Dentist_surgeon;
            case 42 -> Specialization.Pediatrician;
            case 43 -> Specialization.District_pediatrician;
            case 44 -> Specialization.Dentist;
            default -> throw new IllegalArgumentException("Unknown specialization: " + integer);
        };
    }
}
