package ua.com.alevel.hw_10_web_repository.persistence.entity;

public enum Specialization {
    Dentist("Дантист", "Dentist"),
    Dentist_therapist("Дантист-терапевт", "Dentist therapist"),
    Dentist_surgeon ("Дантист-хирург", "Dentist-surgeon"),
    District_pediatrician ("Районный педиатр", "District pediatrician"),
    General_practitioner("Семейный врач", "General practitioner (family doctor)"),
    Local_therapist("Районный терапевт", "Local therapist"),
    Obstetrician_gynecologist("Общий гинеколог", "Obstetrician-gynecologist"),
    Ophthalmologist("Офтальмолог", "Ophthalmologist"),
    Otorhinolaryngologist("Оториноларинголог", "Otorhinolaryngologist"),
    Pediatrician("Педиатр", "Pediatrician"),
    Surgeon("Хирург", "Surgeon"),
    Therapist("Терапевт", "Therapist"),
    Urologist("Уролог", "Urologist");

    private final String value;
    private final String mysqlValue;

    Specialization(String value, String mysqlValue) {
        this.value = value;
        this.mysqlValue = mysqlValue;
    }

    public static Specialization getSpecializationByString(String value) {
        for (Specialization specialization : values()) {
            if (value.equals(specialization.getValue())) {
                return specialization;
            }
        }
        throw new IllegalArgumentException("No such specialization");
    }

    public static Specialization getSpecializationByMySql(String value) {
        value = value.trim();
        for (Specialization specialization : values()) {
            if (value.equals(specialization.getMysqlValue())) {
                return specialization;
            }
        }
        throw new IllegalArgumentException("No such specialization: "+value);
    }

    public String getMysqlValue() {
        return mysqlValue;
    }

    public String getValue() {
        return value;
    }
}
