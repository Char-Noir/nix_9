package ua.com.alevel.entity;

import ua.com.alevel.csv.SerializableEnum;

import java.util.Objects;

public enum Specialization implements SerializableEnum {
    FamilyDoctor("Семейный врач"),
    Therapist("Терапевт"),
    Pediatrician("Педиатр");
    private final String value;

    Specialization(String value) {
        this.value = value;
    }

    public static Specialization getSpecializationByString(String specialization) {
        for (Specialization status : values()) {
            if (Objects.equals(status.getValue(), specialization)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Nu such status");
    }


    public SerializableEnum getByString(String str) {
        return getSpecializationByString(str);
    }

    @Override
    public String toString() {
        return this.value;
    }

    public String getValue() {
        return value;
    }
}
