package ua.com.alevel.web.dto.request.patient;

import org.springframework.format.annotation.DateTimeFormat;
import ua.com.alevel.persistence.entity.patient.Patient;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.web.dto.request.RequestDto;

import java.time.LocalDate;

public class PatientRequestDto extends RequestDto {
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String userDocuments;
    private Long userId;

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

    public Long getUserId() {
        return userId;
    }

    public PatientRequestDto setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Patient getPatient() {
        Patient patient = new Patient();
        patient.setUserDocuments(userDocuments);
        patient.setPhoneNumber(phoneNumber);
        patient.setDateOfBirth(dateOfBirth);
        patient.setName(name);
        patient.setIdUser(new User(userId));
        return patient;
    }

    @Override
    public String toString() {
        return "PatientRequestDto{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userDocuments='" + userDocuments + '\'' +
                ", userId=" + userId +
                '}';
    }
}
