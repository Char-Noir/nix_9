package ua.com.alevel.hw_10_web_repository.dto.impl.reception;

import ua.com.alevel.hw_10_web_repository.dto.impl.doctor.DoctorFullResponseDto;
import ua.com.alevel.hw_10_web_repository.dto.impl.patient.PatientFullResponseDto;
import ua.com.alevel.hw_10_web_repository.persistence.entity.Reception;

public final class ReceptionFullResponseDto extends ReceptionResponseDto {
    private final String reviewComment;
    private final DoctorFullResponseDto doctor;
    private final PatientFullResponseDto patient;

    public ReceptionFullResponseDto(Reception reception, DoctorFullResponseDto doctor, PatientFullResponseDto patient) {
        super(reception, doctor.getDoctor(), patient.getPatient());
        this.reviewComment = reception.getReviewComment();
        this.doctor = doctor;
        this.patient = patient;
    }

    public ReceptionFullResponseDto(Reception reception) {
        super(reception, reception.getDoctor(), reception.getPatient());
        this.reviewComment = reception.getReviewComment();
        this.doctor = new DoctorFullResponseDto(reception.getDoctor());
        this.patient = new PatientFullResponseDto(reception.getPatient());
    }

    public String getReviewComment() {
        return reviewComment;
    }

    public DoctorFullResponseDto getDoctor() {
        return doctor;
    }

    public PatientFullResponseDto getPatient() {
        return patient;
    }

    @Override
    public String toString() {
        return "ReceptionFullResponseDto{" +
                "id=" + id +
                ", reviewComment='" + reviewComment + '\'' +
                ", doctor=" + doctor +
                ", patient=" + patient +
                ", dateTime=" + dateTime +
                ", receptionPrice=" + receptionPrice +
                ", doctor=" + doctor +
                ", patient=" + patient +
                '}';
    }
}
