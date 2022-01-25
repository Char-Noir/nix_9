package ua.com.alevel.hw_9_web_jpa.dto.impl.doctor;

import ua.com.alevel.hw_9_web_jpa.dto.ResponseDto;
import ua.com.alevel.hw_9_web_jpa.persistence.entity.Category;
import ua.com.alevel.hw_9_web_jpa.persistence.entity.Doctor;
import ua.com.alevel.hw_9_web_jpa.persistence.entity.Specialization;
import ua.com.alevel.hw_9_web_jpa.persistence.entity.Status;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DoctorResponseDto extends ResponseDto {
    protected final String name;
    protected final LocalDate dateOfCertification;
    protected final Category category;
    protected final Specialization specialization;
    protected final Status status;
    protected int recCount;

    public DoctorResponseDto(String name, LocalDate dateOfCertification, Category category, Specialization specialization, Status status) {
        this.name = name;
        this.dateOfCertification = dateOfCertification;
        this.category = category;
        this.specialization = specialization;
        this.status = status;
    }

    public DoctorResponseDto(Doctor doctor) {
        this.id = doctor.getIdDoctor();
        this.name = doctor.getName();
        this.dateOfCertification = doctor.getDateOfCertification();
        this.category = doctor.getCategory();
        this.specialization = doctor.getSpecialization();
        this.status = doctor.getStatus();
    }

    public int getRecCount() {
        return recCount;
    }

    public DoctorResponseDto setRecCount(int recCount) {
        this.recCount = recCount;
        return this;
    }

    public boolean getIsOutdated() {
        return dateOfCertification.compareTo(LocalDate.now().minus(5, ChronoUnit.YEARS)) < 0;
    }

    @Override
    public String toString() {
        return "DoctorResponseDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfCertification=" + dateOfCertification +
                ", category=" + category +
                ", specialization=" + specialization +
                ", status=" + status +
                '}';
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfCertification() {
        return dateOfCertification;
    }

    public Category getCategory() {
        return category;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public Status getStatus() {
        return status;
    }
}
