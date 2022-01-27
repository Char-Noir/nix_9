package ua.com.alevel.hw_10_web_repository.dto.impl.reception;

import org.apache.commons.lang3.tuple.Triple;
import ua.com.alevel.hw_10_web_repository.dto.ResponseDto;
import ua.com.alevel.hw_10_web_repository.persistence.entity.Doctor;
import ua.com.alevel.hw_10_web_repository.persistence.entity.Patient;
import ua.com.alevel.hw_10_web_repository.persistence.entity.Reception;

import java.time.LocalDateTime;

public class ReceptionResponseDto extends ResponseDto {
    protected final LocalDateTime dateTime;
    protected final Double receptionPrice;
    protected final DoctorItem doctor;
    protected final PatientItem patient;

    public ReceptionResponseDto(Reception reception, Doctor doctor, Patient patient) {
        this.dateTime = LocalDateTime.of(reception.getDateOfReception(), reception.getReceptionTime());
        this.receptionPrice = reception.getReceptionPrice();
        this.doctor = new DoctorItem(doctor.getName(), doctor.getIdDoctor());
        this.patient = new PatientItem(patient.getName(), patient.getIdPatient());
        this.setId(reception.getIdReception());
    }

    public ReceptionResponseDto(Reception reception) {
        this.dateTime = LocalDateTime.of(reception.getDateOfReception(), reception.getReceptionTime());
        this.receptionPrice = reception.getReceptionPrice();
        this.doctor = new DoctorItem(reception.getDoctor().getName(), reception.getDoctor().getIdDoctor());
        this.patient = new PatientItem(reception.getPatient().getName(), reception.getPatient().getIdPatient());
        this.setId(reception.getIdReception());
    }

    public ReceptionResponseDto(Triple<Reception, Doctor, Patient> triple) {
        this.dateTime = LocalDateTime.of(triple.getLeft().getDateOfReception(), triple.getLeft().getReceptionTime());
        this.receptionPrice = triple.getLeft().getReceptionPrice();
        this.doctor = new DoctorItem(triple.getMiddle().getName(), triple.getMiddle().getIdDoctor());
        this.patient = new PatientItem(triple.getRight().getName(), triple.getRight().getIdPatient());
        this.setId(triple.getLeft().getIdReception());
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

