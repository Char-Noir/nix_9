package ua.com.alevel.hw_10_web_repository.dto.impl.patient;

import org.springframework.format.annotation.DateTimeFormat;
import ua.com.alevel.hw_10_web_repository.dto.RequestDto;
import ua.com.alevel.hw_10_web_repository.persistence.entity.Patient;
import ua.com.alevel.hw_10_web_repository.persistence.entity.Status;

import java.time.LocalDate;

public class PatientRequestDto extends RequestDto {

    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String userDocuments;
    private String login;
    private String hashPassword;

    @Override
    public String toString() {
        return "PatientRequestDto{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userDocuments='" + userDocuments + '\'' +
                ", login='" + login + '\'' +
                ", hashPassword='" + hashPassword + '\'' +
                "} " + super.toString();
    }

    public String getName() {
        return name;
    }

    public PatientRequestDto setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public PatientRequestDto setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public PatientRequestDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getUserDocuments() {
        return userDocuments;
    }

    public PatientRequestDto setUserDocuments(String userDocuments) {
        this.userDocuments = userDocuments;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public PatientRequestDto setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public PatientRequestDto setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
        return this;
    }

    public Patient getPatient() {
        return new Patient()
                .setName(name)
                .setDateOfBirth(dateOfBirth)
                .setPhoneNumber(phoneNumber)
                .setUserDocuments(userDocuments)
                .setLogin(login)
                .setHashPassword(hashPassword)
                .setStatus(Status.Patient);
    }
}
