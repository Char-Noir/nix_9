package ua.com.alevel.hw_8_web_jdbc.dto.impl.reception;

import org.apache.commons.lang3.tuple.Triple;
import ua.com.alevel.hw_8_web_jdbc.dto.ResponseDto;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Doctor;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Patient;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Reception;

import java.time.LocalDateTime;

public class ReceptionResponseDto extends ResponseDto {
    protected final LocalDateTime dateTime;
    protected final Double receptionPrice;
    protected final DoctorItem doctor;
    protected final PatientItem patient;

    public ReceptionResponseDto(Reception reception, Doctor doctor, Patient patient) {
        this.dateTime = LocalDateTime.of(reception.getDateOfReception(), reception.getReceptionTime());
        this.receptionPrice = reception.getReceptionPrice();
        this.doctor = new DoctorItem(doctor.getName(), doctor.getId());
        this.patient = new PatientItem(patient.getName(), patient.getId());
        this.setId(reception.getId());
    }

    public ReceptionResponseDto(Triple<Reception, Doctor, Patient> triple) {
        this.dateTime = LocalDateTime.of(triple.getLeft().getDateOfReception(), triple.getLeft().getReceptionTime());
        this.receptionPrice = triple.getLeft().getReceptionPrice();
        this.doctor = new DoctorItem(triple.getMiddle().getName(), triple.getMiddle().getId());
        this.patient = new PatientItem(triple.getRight().getName(), triple.getRight().getId());
        this.setId(triple.getLeft().getId());
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Double getReceptionPrice() {
        return receptionPrice;
    }

    public DoctorItem getDoctorItem() {
        return doctor;
    }

    public PatientItem getPatientItem() {
        return patient;
    }

    public record DoctorItem(String name, Long id) {
        public String getName() {
            return name;
        }

        public Long getId() {
            return id;
        }
    }

    public record PatientItem(String name, Long id) {
        public String getName() {
            return name;
        }

        public Long getId() {
            return id;
        }
    }
}

