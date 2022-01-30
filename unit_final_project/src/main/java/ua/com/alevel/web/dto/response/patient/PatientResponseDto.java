package ua.com.alevel.web.dto.response.patient;

import ua.com.alevel.persistence.entity.patient.Patient;
import ua.com.alevel.web.dto.response.ResponseDto;
import ua.com.alevel.web.dto.response.user.UserResponseDto;

import java.time.LocalDate;

public class PatientResponseDto extends ResponseDto {
    private String name;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String userDocuments;
    private UserResponseDto user;

    public PatientResponseDto(Patient patient) {
        super(patient.getId());
        this.name = patient.getName();
        this.dateOfBirth = patient.getDateOfBirth();
        this.phoneNumber = patient.getPhoneNumber();
        this.userDocuments = patient.getUserDocuments();
        this.user = new UserResponseDto(patient.getIdUser());
    }

    public String getName() {
        return name;
    }

    public PatientResponseDto setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public PatientResponseDto setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public PatientResponseDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getUserDocuments() {
        return userDocuments;
    }

    public PatientResponseDto setUserDocuments(String userDocuments) {
        this.userDocuments = userDocuments;
        return this;
    }

    public UserResponseDto getUser() {
        return user;
    }

    public PatientResponseDto setUser(UserResponseDto user) {
        this.user = user;
        return this;
    }

}
