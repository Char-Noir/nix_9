package ua.com.alevel.hw_8_web_jdbc.persistence.entity;

import ua.com.alevel.logic.entity.BaseEntity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Doctor extends BaseEntity {

    private String name;
    private Double assessmentOfCertification;
    private String doctorNote;
    private LocalDate dateOfCertification;
    private String login;
    private String hashPassword;
    private Status status;
    private Category category;
    private Specialization specialization;

    public Doctor() {
    }

    public Doctor(String name, Double assessmentOfCertification, String doctorNote, LocalDate dateOfCertification, String login, String hashPassword, Category category, Specialization specialization) {
        this.name = name;
        this.assessmentOfCertification = assessmentOfCertification;
        this.doctorNote = doctorNote;
        this.dateOfCertification = dateOfCertification;
        this.login = login;
        this.hashPassword = hashPassword;
        this.status = Status.Doctor;
        this.category = category;
        this.specialization = specialization;
    }

    public Doctor(Long doctorId) {
        setId(doctorId);
    }

    public String getName() {
        LocalDate.of(2012,01,17).compareTo(LocalDate.now().minus(5,ChronoUnit.YEARS));
        return name;
    }

    public Doctor setName(String name) {
        this.name = name;
        return this;
    }

    public Double getAssessmentOfCertification() {
        return assessmentOfCertification;
    }

    public Doctor setAssessmentOfCertification(Double assessmentOfCertification) {
        this.assessmentOfCertification = assessmentOfCertification;
        return this;
    }

    public String getDoctorNote() {
        return doctorNote;
    }

    public Doctor setDoctorNote(String doctorNote) {
        this.doctorNote = doctorNote;
        return this;
    }

    public LocalDate getDateOfCertification() {
        return dateOfCertification;
    }

    public Doctor setDateOfCertification(LocalDate dateOfCertification) {
        this.dateOfCertification = dateOfCertification;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public Doctor setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public Doctor setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public Doctor setStatus(Status status) {
        this.status = status;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Doctor setCategory(Category category) {
        this.category = category;
        return this;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public Doctor setSpecialization(Specialization specialization) {
        this.specialization = specialization;
        return this;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", assessmentOfCertification=" + assessmentOfCertification +
                ", doctorNote='" + doctorNote + '\'' +
                ", dateOfCertification=" + dateOfCertification +
                ", login='" + login + '\'' +
                ", hashPassword='" + hashPassword + '\'' +
                ", status=" + status +
                ", category=" + category +
                ", specialization=" + specialization +
                '}' + '\n';
    }
}
