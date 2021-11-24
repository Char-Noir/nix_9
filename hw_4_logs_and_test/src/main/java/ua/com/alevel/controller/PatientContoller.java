package ua.com.alevel.controller;

import ua.com.alevel.console.responce.Responce;
import ua.com.alevel.entity.Patient;

import java.time.LocalDate;

public interface PatientContoller extends BaseController<Patient> {

    Responce create(String name, LocalDate dateOfBirth, String phoneNumber, String userDocuments, String login, String hashPassword);

    Responce update(Long id, String name, LocalDate dateOfBirth, String phoneNumber, String userDocuments, String login, String hashPassword);

    Responce delete(long id);

    Responce findById(long id);

    Responce findAll();
}
