package ua.com.alevel.persistence.entity.doctor;

import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.Worker;
import ua.com.alevel.persistence.entity.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "doctor", indexes = {
        @Index(name = "d_cat_idx", columnList = "id_category"),
        @Index(name = "doctor_name_category", columnList = "name, id_category, id_specializations"),
        @Index(name = "d_spec_idx", columnList = "id_specializations"),
        @Index(name = "doctor_id_user_uindex", columnList = "id_user", unique = true)
})
@Entity
public class Doctor extends BaseEntity implements Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doctor", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "assessment_of_certification", nullable = false)
    private Float assessmentOfCertification;

    @Lob
    @Column(name = "doctor_note", nullable = false)
    private String doctorNote;

    @Column(name = "date_of_certification", nullable = false)
    private LocalDate dateOfCertification;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_category", nullable = false)
    private Category category;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_specializations", nullable = false)
    private Specialization specialization;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User idUser) {
        this.user = idUser;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization idSpecializations) {
        this.specialization = idSpecializations;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category idCategory) {
        this.category = idCategory;
    }

    public LocalDate getDateOfCertification() {
        return dateOfCertification;
    }

    public void setDateOfCertification(LocalDate dateOfCertification) {
        this.dateOfCertification = dateOfCertification;
    }

    public String getDoctorNote() {
        return doctorNote;
    }

    public void setDoctorNote(String doctorNote) {
        this.doctorNote = doctorNote;
    }

    public Float getAssessmentOfCertification() {
        return assessmentOfCertification;
    }

    public void setAssessmentOfCertification(Float assessmentOfCertification) {
        this.assessmentOfCertification = assessmentOfCertification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}