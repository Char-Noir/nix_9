package ua.com.alevel.entity;

import ua.com.alevel.logic.entity.BaseEntity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reception extends BaseEntity {

    private LocalDate dateOfReception;
    private LocalTime receptionTime;
    private String reviewComment;
    private Double receptionPrice;
    private Long doctorId;
    private Long patientId;

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
                "id=" + id +
                ", dateOfReception=" + dateOfReception +
                ", receptionTime=" + receptionTime +
                ", reviewComment='" + reviewComment + '\'' +
                ", receptionPrice=" + receptionPrice +
                ", doctorId=" + doctorId +
                ", patientId=" + patientId +
                '}'+'\n';
    }
}
