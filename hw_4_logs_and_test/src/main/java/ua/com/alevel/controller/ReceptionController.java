package ua.com.alevel.controller;

import ua.com.alevel.console.responce.Responce;
import ua.com.alevel.entity.Reception;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ReceptionController extends BaseController<Reception> {

    Responce create(LocalDate dateOfReception, LocalTime receptionTime, String reviewComment, Double receptionPrice, Long doctorId, Long patientId);

    Responce update(Long id, LocalDate dateOfReception, LocalTime receptionTime, String reviewComment, Double receptionPrice);

    Responce delete(long id);

    Responce findById(long id);

    Responce findAll();

    Responce findByPatient(Long patientId);

    Responce findByDoctor(Long doctorId);
}
