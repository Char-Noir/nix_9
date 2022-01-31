package ua.com.alevel.web.dto.response.reception;

import ua.com.alevel.persistence.entity.reception.Reception;
import ua.com.alevel.web.dto.response.ResponseDto;
import ua.com.alevel.web.dto.response.doctor.DoctorResponseDto;
import ua.com.alevel.web.dto.response.patient.PatientResponseDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ReceptionResponseDto extends ResponseDto {
    LocalDateTime dateTime;
    String reviewComment;
    Float receptionPrice;
    PatientResponseDto patient;
    DoctorResponseDto doctor;

    public ReceptionResponseDto(Reception reception) {
        super(reception.getId());
        setDateTime(reception.getDateOfReception(), reception.getReceptionTime());
        reviewComment = reception.getReviewComment();
        receptionPrice = reception.getReceptionPrice();
        patient = new PatientResponseDto(reception.getPatient());
        doctor = new DoctorResponseDto(reception.getDoctor());
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public ReceptionResponseDto setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public ReceptionResponseDto setDateTime(LocalDate date, LocalTime time) {
        this.dateTime = LocalDateTime.of(date, time);
        return this;
    }

    public String getReviewComment() {
        return reviewComment;
    }

    public ReceptionResponseDto setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
        return this;
    }

    public Float getReceptionPrice() {
        return receptionPrice;
    }

    public ReceptionResponseDto setReceptionPrice(Float receptionPrice) {
        this.receptionPrice = receptionPrice;
        return this;
    }

    public PatientResponseDto getPatient() {
        return patient;
    }

    public ReceptionResponseDto setPatient(PatientResponseDto patient) {
        this.patient = patient;
        return this;
    }

    public DoctorResponseDto getDoctor() {
        return doctor;
    }

    public ReceptionResponseDto setDoctor(DoctorResponseDto doctor) {
        this.doctor = doctor;
        return this;
    }
}
