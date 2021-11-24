package ua.com.alevel.entity;

import ua.com.alevel.logic.entity.BaseEntity;

import java.time.LocalDate;

public class Patient extends BaseEntity {

    private String name;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String userDocuments;
    private String login;
    private String hashPassword;
    private Status status;

    public Patient(Long patientId) {
        setId(patientId);
    }

    public Patient(String name, LocalDate dateOfBirth, String phoneNumber, String userDocuments, String login, String hashPassword) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.userDocuments = userDocuments;
        this.login = login;
        this.hashPassword = hashPassword;
    }

    public Patient() {
    }

    public String getName() {
        return name;
    }

    public Patient setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Patient setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Patient setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getUserDocuments() {
        return userDocuments;
    }

    public Patient setUserDocuments(String userDocuments) {
        this.userDocuments = userDocuments;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public Patient setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public Patient setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
        return this;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userDocuments='" + userDocuments + '\'' +
                ", login='" + login + '\'' +
                ", hashPassword='" + hashPassword + '\'' +
                ", status=" + status +
                '}' + '\n';
    }

    public Status getStatus() {
        return status;
    }

    public Patient setStatus(Status status) {
        this.status = status;
        return this;
    }
}
