package ua.com.alevel.hw_10_web_repository.dto.impl.patient;

import ua.com.alevel.hw_10_web_repository.persistence.entity.Patient;

public final class PatientFullResponseDto extends PatientResponseDto {

    private final String userDocuments;
    private final String login;
    private final String password;

    public PatientFullResponseDto(Patient patient) {
        super(patient);
        login = patient.getLogin();
        userDocuments = patient.getUserDocuments();
        password = patient.getHashPassword();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "PatientFullResponseDto{" +
                "userDocuments='" + userDocuments + '\'' +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", status=" + status +
                ", recCount=" + recCount +
                '}';
    }

    public String getUserDocuments() {
        return userDocuments;
    }

    public String getLogin() {
        return login;
    }

    public Patient getPatient() {
        return new Patient(id)
                .setName(name)
                .setDateOfBirth(dateOfBirth)
                .setStatus(status)
                .setLogin(login)
                .setUserDocuments(userDocuments)
                .setPhoneNumber(phoneNumber)
                .setHashPassword(password);
    }
}
