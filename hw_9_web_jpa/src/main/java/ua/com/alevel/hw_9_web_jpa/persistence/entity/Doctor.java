package ua.com.alevel.hw_9_web_jpa.persistence.entity;


import ua.com.alevel.hw_9_web_jpa.persistence.converter.CategoryAttributeConverter;
import ua.com.alevel.hw_9_web_jpa.persistence.converter.SpecializationAttributeConverter;
import ua.com.alevel.hw_9_web_jpa.persistence.converter.StatusAttributeConverter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "doctor")
public class Doctor extends BaseEntity {

    @Column(name = "id_doctor", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idDoctor;

    @Column(name = "name")
    private String name;
    @Column(name = "assessment_of_certification")
    private Double assessmentOfCertification;
    @Column(name = "doctor_note")
    private String doctorNote;
    @Column(name = "date_of_certification")
    private LocalDate dateOfCertification;
    @Column(name = "login")
    private String login;
    @Column(name = "hash_password")
    private String hashPassword;
    @Convert(converter = StatusAttributeConverter.class)
    @Column(name = "id_status")
    private Status status;
    @Convert(converter = CategoryAttributeConverter.class)
    @Column(name = "id_category")
    private Category category;
    @Convert(converter = SpecializationAttributeConverter.class)
    @Column(name = "id_specializations")
    private Specialization specialization;

    @OneToMany(mappedBy = "doctor", orphanRemoval = true)
    private List<Reception> receptions = new java.util.ArrayList<>();
    @OneToMany(mappedBy = "doctor", orphanRemoval = true)
    private List<DoctorPatient> declarations = new java.util.ArrayList<>();

    public Doctor() {
    }

    public Doctor(String name, Double assessmentOfCertification, String doctorNote, LocalDate dateOfCertification, String login, String hashPassword, Category category, Specialization specialization, List<Reception> receptions) {
        this.name = name;
        this.assessmentOfCertification = assessmentOfCertification;
        this.doctorNote = doctorNote;
        this.dateOfCertification = dateOfCertification;
        this.login = login;
        this.hashPassword = hashPassword;
        this.receptions = receptions;
        this.status = Status.Doctor;
        this.category = category;
        this.specialization = specialization;
    }

    public Doctor(Long doctorId) {
        setIdDoctor(doctorId);
    }

    public Long getIdDoctor() {
        return idDoctor;
    }

    public Doctor setIdDoctor(Long id) {
        this.idDoctor = id;
        return this;
    }

    public List<DoctorPatient> getDeclarations() {
        return declarations;
    }

    public Doctor setDeclarations(List<DoctorPatient> declarations) {
        this.declarations = declarations;
        return this;
    }

    public List<Reception> getReceptions() {

        return receptions;
    }

    public Doctor setReceptions(List<Reception> receptions) {
        this.receptions = receptions;
        return this;
    }

    public String getName() {
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
                "id=" + idDoctor +
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
