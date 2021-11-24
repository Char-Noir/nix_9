package ua.com.alevel.controller.impl;

import ua.com.alevel.console.responce.Responce;
import ua.com.alevel.controller.PatientContoller;
import ua.com.alevel.entity.Patient;
import ua.com.alevel.entity.Status;
import ua.com.alevel.service.PatientService;
import ua.com.alevel.service.impl.PatientServiceImpl;

import java.time.LocalDate;

public class PatientControllerImpl implements PatientContoller {

    PatientService patientService = new PatientServiceImpl();

    @Override
    public Responce create(String name, LocalDate dateOfBirth, String phoneNumber, String userDocuments, String login, String hashPassword) {
        Patient patient;
        try {
            patient = new Patient()
                    .setName(name)
                    .setDateOfBirth(dateOfBirth)
                    .setPhoneNumber(phoneNumber)
                    .setUserDocuments(userDocuments)
                    .setLogin(login)
                    .setHashPassword(hashPassword)
                    .setStatus(Status.Patient);
            patientService.create(patient);
            return Responce.getRedirect(new StringBuilder("Patient created successfully: ").append(patient).toString(), "ShowAll/patients");
        } catch (Exception e) {
            return Responce.getError("Error when creating patient", e.getMessage());
        }

    }

    @Override
    public Responce update(Long id, String name, LocalDate dateOfBirth, String phoneNumber, String userDocuments, String login, String hashPassword) {
        Patient patient;
        try {
            patient = new Patient()
                    .setName(name)
                    .setDateOfBirth(dateOfBirth)
                    .setPhoneNumber(phoneNumber)
                    .setUserDocuments(userDocuments)
                    .setLogin(login)
                    .setHashPassword(hashPassword)
                    .setStatus(Status.Patient);
            patient.setId(id);
            if (patientService.update(patient)) {
                return Responce.getRedirect("Patient updated", "ShowAll/patients");
            } else {
                return Responce.getError("Patient not updated", "Patient not found");
            }
        } catch (Exception e) {
            return Responce.getError("Patient not updated", e.getMessage());
        }
    }

    @Override
    public Responce delete(long id) {
        try {
            if (patientService.delete(id)) {
                return Responce.getRedirect("Patient deleted", "ShowAll/patients");
            } else {
                return Responce.getError("Patient not deleted", "Patient not found");
            }
        } catch (Exception e) {
            return Responce.getError("Patient not deleted", e.getMessage());
        }
    }

    @Override
    public Responce findById(long id) {
        Patient patient;
        try {
            patient = patientService.findById(id);
            if (patient != null) {
                return Responce.getOk(patient.toString());
            } else {
                return Responce.getError("Patient not found", "Patient equals null");
            }
        } catch (Exception e) {
            return Responce.getError("Patient not found", e.getMessage());
        }
    }

    @Override
    public Responce findAll() {
        Patient[] patients = patientService.findAll();
        if (patients.length == 0) {
            return Responce.getOk("No patients in app");
        }
        StringBuilder stringBuilder = new StringBuilder("Пациенты:\n");
        for (Patient patient :
                patients) {
            if (patient.getStatus() != Status.Deleted) {
                stringBuilder.append(patient);
            } else {
                stringBuilder.append("Patient Deleted with id: ").append(patient.getId()).append('\n');
            }
        }
        return Responce.getOk(stringBuilder.toString());
    }

    @Override
    public void initDB() {
        //
    }
}