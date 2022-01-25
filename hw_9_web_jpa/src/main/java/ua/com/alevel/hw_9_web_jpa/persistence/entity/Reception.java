package ua.com.alevel.hw_9_web_jpa.persistence.entity;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "reception")
public class Reception extends BaseEntity {


    @Column(name = "id_reception", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idReception;
    @Column(name = "date_of_reception")
    private LocalDate dateOfReception;
    @Column(name = "reception_time")
    private LocalTime receptionTime;
    @Column(name = "review_comment")
    private String reviewComment;
    @Column(name = "reception_price")
    private Double receptionPrice;
    private Long doctorId;
    private Long patientId;
    @ManyToOne
    @JoinColumn(name = "id_doctor")
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "id_patient")
    private Patient patient;

    public Reception() {
    }

    public Reception(Long idReception) {
        setIdReception(idReception);
    }

    public Long getIdReception() {
        return idReception;
    }

    public Reception setIdReception(Long id) {
        this.idReception = id;
        return this;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public Reception setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
        return this;
    }

    public Long getPatientId() {
        return patientId;
    }

    public Reception setPatientId(Long patientId) {
        this.patientId = patientId;
        return this;
    }

    public LocalDate getDateOfReception() {
        return dateOfReception;
    }

    public Reception setDateOfReception(LocalDate dateOfReception) {
        this.dateOfReception = dateOfReception;
        return this;
    }

    public LocalTime getReceptionTime() {
        return receptionTime;
    }

    public Reception setReceptionTime(LocalTime receptionTime) {
        this.receptionTime = receptionTime;
        return this;
    }

    public String getReviewComment() {
        return reviewComment;
    }

    public Reception setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
        return this;
    }

    public Double getReceptionPrice() {
        return receptionPrice;
    }

    public Reception setReceptionPrice(Double receptionPrice) {
        this.receptionPrice = receptionPrice;
        return this;
    }

    @Override
    public String toString() {
        return "Reception{" +
                "id=" + idReception +
                ", dateOfReception=" + dateOfReception +
                ", receptionTime=" + receptionTime +
                ", reviewComment='" + reviewComment + '\'' +
                ", receptionPrice=" + receptionPrice +
                ", doctorId=" + doctorId +
                ", patientId=" + patientId +
                '}' + '\n';
    }

    public void update(Reception entity) {
        if (entity.getIdReception() != null && !Objects.equals(entity.getIdReception(), idReception)) {
            throw new RuntimeException("Can`t update different receptions");
        }
        if (entity.getDateOfReception() != null) {
            this.setDateOfReception(entity.getDateOfReception());
        }
        if (entity.getReceptionTime() != null) {
            this.setReceptionTime(entity.getReceptionTime());
        }
        if (entity.getReviewComment() != null) {
            this.setReviewComment(entity.getReviewComment());
        }
        if (entity.getReceptionPrice() != null) {
            this.setReceptionPrice(entity.getReceptionPrice());
        }
    }
}
