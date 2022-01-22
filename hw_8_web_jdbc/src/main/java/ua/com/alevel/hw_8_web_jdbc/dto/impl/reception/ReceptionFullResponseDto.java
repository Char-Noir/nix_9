package ua.com.alevel.hw_8_web_jdbc.dto.impl.reception;

import ua.com.alevel.hw_8_web_jdbc.dto.impl.doctor.DoctorFullResponseDto;
import ua.com.alevel.hw_8_web_jdbc.dto.impl.patient.PatientFullResponseDto;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Doctor;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Patient;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Reception;

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
