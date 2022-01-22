package ua.com.alevel.hw_8_web_jdbc.dto.impl.patient;

import ua.com.alevel.hw_8_web_jdbc.dto.ResponseDto;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Patient;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Status;

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
        this.setId(patient.getId());
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

    public void setRecCount(Integer integer) {
        this.recCount = integer;
    }

    public int getRecCount() {
        return recCount;
    }
}
