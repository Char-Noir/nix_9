package ua.com.alevel.hw_9_web_jpa.dto.impl.doctor;

import org.springframework.format.annotation.DateTimeFormat;
import ua.com.alevel.hw_9_web_jpa.dto.RequestDto;
import ua.com.alevel.hw_9_web_jpa.persistence.entity.Category;
import ua.com.alevel.hw_9_web_jpa.persistence.entity.Doctor;
import ua.com.alevel.hw_9_web_jpa.persistence.entity.Specialization;
import ua.com.alevel.hw_9_web_jpa.persistence.entity.Status;

import java.time.LocalDate;

public class DoctorRequestDto extends RequestDto {
    protected final String name;
    protected final Double assessmentOfCertification;
    protected final String note;
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    protected final LocalDate dateOfCertification;
    protected final String login;
    protected final String password;
    protected final Status status = Status.Doctor;
    protected final Category category;
    protected final Specialization specialization;

    public DoctorRequestDto(String name, Double assessmentOfCertification, String note, LocalDate dateOfCertification, String login, String password, Status status, Category category, Specialization specialization) {
        this.name = name;
        this.assessmentOfCertification = assessmentOfCertification;
        this.note = note;
        this.dateOfCertification = dateOfCertification;
        this.login = login;
        this.password = password;
        this.category = category;
        this.specialization = specialization;
    }

    public Doctor getDoctor() {
        return new Doctor()
                .setName(name)
                .setAssessmentOfCertification(assessmentOfCertification)
                .setDoctorNote(note)
                .setDateOfCertification(dateOfCertification)
                .setLogin(login)
                .setHashPassword(password)
                .setStatus(status)
                .setCategory(category)
                .setSpecialization(specialization);
    }

    @Override
    public String toString() {
        return "DoctorRequestDto{" +
                "name='" + name + '\'' +
                ", assessmentOfCertification=" + assessmentOfCertification +
                ", note='" + note + '\'' +
                ", dateOfCertification=" + dateOfCertification +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", category=" + category +
                ", specialization=" + specialization +
                '}';
    }

    public String getName() {
        return name;
    }

    public Double getAssessmentOfCertification() {
        return assessmentOfCertification;
    }

    public String getNote() {
        return note;
    }

    public LocalDate getDateOfCertification() {
        return dateOfCertification;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Status getStatus() {
        return status;
    }

    public Category getCategory() {
        return category;
    }

    public Specialization getSpecialization() {
        return specialization;
    }
}
