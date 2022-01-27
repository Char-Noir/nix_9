package ua.com.alevel.hw_10_web_repository.dto.impl.patient;

import ua.com.alevel.hw_10_web_repository.dto.ResponseDto;
import ua.com.alevel.hw_10_web_repository.persistence.entity.Patient;
import ua.com.alevel.hw_10_web_repository.persistence.entity.Status;

import java.time.LocalDate;

public class PatientResponseDto extends ResponseDto {

    protected final String name;
    protected final LocalDate dateOfBirth;
    protected final String phoneNumber;
    protected final Status status;
    protected int recCount;

    public PatientResponseDto(Patient patient) {
        this.name = patient.getName();
        this.dateOfBirth = patient.getDateOfBirth();
        this.phoneNumber = patient.getPhoneNumber();
        this.status = patient.getStatus();
        this.setId(patient.getIdPatient());
        this.setRecCount(patient.getReceptions().size());
        System.out.println(patient.getReceptions());
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Status getStatus() {
        return status;
    }

    public int getRecCount() {
        return recCount;
    }

    public void setRecCount(Integer integer) {
        this.recCount = integer;
    }
}
