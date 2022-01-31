package ua.com.alevel.web.dto.request.reception;

import org.springframework.format.annotation.DateTimeFormat;
import ua.com.alevel.persistence.entity.doctor.Doctor;
import ua.com.alevel.persistence.entity.patient.Patient;
import ua.com.alevel.persistence.entity.reception.Reception;
import ua.com.alevel.web.dto.request.RequestDto;

import java.time.LocalDateTime;

public class ReceptionRequestDto extends RequestDto {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime dateTime;
    String reviewComment;
    Float receptionPrice;
    Long patient;
    Long doctor;

    public Reception getReception() {
        Reception reception = new Reception();
        reception.setDateOfReception(dateTime.toLocalDate());
        reception.setReceptionTime(dateTime.toLocalTime());
        reception.setReviewComment(reviewComment);
        reception.setReceptionPrice(receptionPrice);
        reception.setDoctor(new Doctor(doctor));
        reception.setPatient(new Patient(patient));
        return reception;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public ReceptionRequestDto setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public String getReviewComment() {
        return reviewComment;
    }

    public ReceptionRequestDto setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
        return this;
    }

    public Float getReceptionPrice() {
        return receptionPrice;
    }

    public ReceptionRequestDto setReceptionPrice(Float receptionPrice) {
        this.receptionPrice = receptionPrice;
        return this;
    }

    public Long getPatient() {
        return patient;
    }

    public ReceptionRequestDto setPatient(Long patient) {
        this.patient = patient;
        return this;
    }

    public Long getDoctor() {
        return doctor;
    }

    public ReceptionRequestDto setDoctor(Long doctor) {
        this.doctor = doctor;
        return this;
    }
}
