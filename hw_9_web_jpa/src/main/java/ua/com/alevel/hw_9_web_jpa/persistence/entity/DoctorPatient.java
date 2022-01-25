package ua.com.alevel.hw_9_web_jpa.persistence.entity;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "doctor_patient", indexes = {
        @Index(name = "fk_Doctor_has_Patient_Doctor1_idx", columnList = "id_doctor"),
        @Index(name = "fk_Doctor_has_Patient_Patient1_idx", columnList = "id_patient")
})
@Entity
public class DoctorPatient extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(name = "create_date", nullable = false)
    private Instant createDate;
    @Column(name = "end_date", nullable = false)
    private Instant endDate;
    @ManyToOne
    @JoinColumn(name = "id_doctor")
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "id_patient")
    private Patient patient;

    public Patient getPatient() {
        return patient;
    }

    public DoctorPatient setPatient(Patient patient) {
        this.patient = patient;
        return this;
    }

    public Long getId() {
        return id;
    }

    public DoctorPatient setId(Long id) {
        this.id = id;
        return this;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

}