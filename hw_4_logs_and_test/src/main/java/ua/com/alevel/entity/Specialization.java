package ua.com.alevel.entity;

public enum Specialization {
    FamilyDoctor("Семейный врач"),
    Therapist("Терапевт"),
    Pediatrician("Педиатр");
    private final String value;

    Specialization(String value) {
        this.value = value;
    }

    public static Specialization getSpecializationByString(String value) {
        for (Specialization specialization : values()) {
            if (value.equals(specialization.getValue())) {
                return specialization;
            }
        }
        throw new IllegalArgumentException("No such specialization");
    }

    public String getValue() {
        return value;
    }
}
