package ua.com.alevel.persistence.entity.reception;

import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.doctor.Doctor;
import ua.com.alevel.persistence.entity.patient.Patient;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Table(name = "reception", indexes = {
        @Index(name = "rec_pat_fk_idx", columnList = "id_patient"),
        @Index(name = "reception_date_doctor", columnList = "date_of_reception, id_doctor"),
        @Index(name = "reception_F_review", columnList = "review_comment"),
        @Index(name = "rec_doc_fk_idx", columnList = "id_doctor")
})
@Entity
public class Reception extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reception", nullable = false)
    private Long id;

    @Column(name = "date_of_reception", nullable = false)
    private LocalDate dateOfReception;

    @Column(name = "reception_time", nullable = false)
    private LocalTime receptionTime;

    @Lob
    @Column(name = "review_comment", nullable = false)
    private String reviewComment;

    @Column(name = "reception_price", nullable = false)
    private Float receptionPrice;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_doctor", nullable = false)
    private Doctor doctor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_patient", nullable = false)
    private Patient patient;

    @Column(name = "doctorId")
    private Long doctorId;

    @Column(name = "patientId")
    private Long patientId;

    @Column(name = "datetime")
    private LocalDateTime datetime;

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
        this.dateOfReception = datetime.toLocalDate();
        this.receptionTime = datetime.toLocalTime();
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient idPatient) {
        this.patient = idPatient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor idDoctor) {
        this.doctor = idDoctor;
    }

    public Float getReceptionPrice() {
        return receptionPrice;
    }

    public void setReceptionPrice(Float receptionPrice) {
        this.receptionPrice = receptionPrice;
    }

    public String getReviewComment() {
        return reviewComment;
    }

    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }

    public LocalTime getReceptionTime() {
        return receptionTime;
    }

    public void setReceptionTime(LocalTime receptionTime) {
        this.datetime = LocalDateTime.of(datetime.toLocalDate(), receptionTime);
        this.receptionTime = receptionTime;
    }

    public LocalDate getDateOfReception() {
        return dateOfReception;
    }

    public void setDateOfReception(LocalDate dateOfReception) {
        this.datetime = LocalDateTime.of(dateOfReception, datetime.toLocalTime());
        this.dateOfReception = dateOfReception;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}