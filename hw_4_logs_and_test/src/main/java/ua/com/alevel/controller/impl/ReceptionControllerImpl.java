package ua.com.alevel.controller.impl;

import ua.com.alevel.console.responce.Responce;
import ua.com.alevel.controller.ReceptionController;
import ua.com.alevel.entity.*;
import ua.com.alevel.service.DoctorService;
import ua.com.alevel.service.PatientService;
import ua.com.alevel.service.ReceptionService;
import ua.com.alevel.service.impl.DoctorServiceImpl;
import ua.com.alevel.service.impl.PatientServiceImpl;
import ua.com.alevel.service.impl.ReceptionServiceImpl;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReceptionControllerImpl implements ReceptionController {

    ReceptionService receptionService = new ReceptionServiceImpl();
    PatientService patientService = new PatientServiceImpl();
    DoctorService doctorService = new DoctorServiceImpl();

    @Override
    public Responce create(LocalDate dateOfReception, LocalTime receptionTime, String reviewComment, Double receptionPrice, Long doctorId, Long patientId) {
        Reception reception;
        try {
            Patient patient = patientService.findById(patientId);
            Doctor doctor = doctorService.findById(doctorId);
            reception = new Reception()
                    .setDateOfReception(dateOfReception)
                    .setReceptionTime(receptionTime)
                    .setReviewComment(reviewComment)
                    .setReceptionPrice(receptionPrice)
                    .setDoctorId(doctorId)
                    .setPatientId(patientId);
            receptionService.create(reception);
            return Responce.getRedirect(new StringBuilder("Reception created successfully: ").append(reception).toString(), "ShowAll/receptions");
        } catch (Exception e) {
            return Responce.getError("Error when creating Reception", e.getMessage());
        }

    }

    @Override
    public Responce update(Long id, LocalDate dateOfReception, LocalTime receptionTime, String reviewComment, Double receptionPrice) {
        Reception reception;
        try {
            Reception rec = receptionService.findById(id);
            Patient patient = patientService.findById(rec.getPatientId());
            Doctor doctor = doctorService.findById(rec.getDoctorId());
            reception = new Reception()
                    .setDateOfReception(dateOfReception)
                    .setReceptionTime(receptionTime)
                    .setReviewComment(reviewComment)
                    .setReceptionPrice(receptionPrice)
                    .setDoctorId(doctor.getId())
                    .setPatientId(patient.getId());
            reception.setId(id);
            if (receptionService.update(reception)) {
                return Responce.getRedirect("Reception updated", "ShowAll/receptions");
            } else {
                return Responce.getError("Reception not updated", "Reception not found");
            }
        } catch (Exception e) {
            return Responce.getError("Reception not updated", e.getMessage());
        }
    }

    @Override
    public Responce delete(long id) {
        try {
            if (receptionService.delete(id)) {
                return Responce.getRedirect("Reception deleted", "ShowAll/receptions");
            } else {
                return Responce.getError("Reception not deleted", "Doctor not found");
            }
        } catch (Exception e) {
            return Responce.getError("Reception not deleted", e.getMessage());
        }
    }

    @Override
    public Responce findById(long id) {
        Reception reception;
        try {
            reception = receptionService.findById(id);
            if (reception != null) {
                return Responce.getOk(reception.toString());
            } else {
                return Responce.getError("Reception not found", "Reception equals null");
            }
        } catch (Exception e) {
            return Responce.getError("Reception not found", e.getMessage());
        }
    }

    @Override
    public Responce findAll() {
        Reception[] receptionsoctors = receptionService.findAll();
        if (receptionsoctors.length == 0) {
            return Responce.getOk("No Receptions in app");
        }
        StringBuilder stringBuilder = new StringBuilder("Receptions:\n");
        for (Reception reception :
                receptionsoctors) {
            stringBuilder.append(reception);
        }
        return Responce.getOk(stringBuilder.toString());
    }

    @Override
    public Responce findByPatient(Long patientId) {
        Reception[] receptionsoctors = receptionService.findReceptionByPatient(new Patient(patientId));
        if (receptionsoctors.length == 0) {
            return Responce.getOk("No receptions in app");
        }
        StringBuilder stringBuilder = new StringBuilder("Receptions:\n");
        for (Reception reception :
                receptionsoctors) {
            stringBuilder.append(reception);
        }
        return Responce.getOk(stringBuilder.toString());
    }

    @Override
    public Responce findByDoctor(Long doctorId) {
        Reception[] receptionsoctors = receptionService.findReceptionByDoctor(new Doctor(doctorId));
        if (receptionsoctors.length == 0) {
            return Responce.getOk("No receptions in app");
        }
        StringBuilder stringBuilder = new StringBuilder("Receptions:\n");
        for (Reception reception :
                receptionsoctors) {
            stringBuilder.append(reception);
        }
        return Responce.getOk(stringBuilder.toString());
    }

    @Override
    public void initDB() {
        DoctorService doctorService = new DoctorServiceImpl();
        doctorService.create(new Doctor("Doctor 1", 18.3d, "Good doctor", LocalDate.of(2021, 11, 24), "kittyHall", "fdfdfdfdf", Category.First, Specialization.FamilyDoctor).setStatus(Status.Doctor));
        doctorService.create(new Doctor("Doctor 2", 10d, "Not good doctor", LocalDate.of(2021, 11, 23), "mow", "4dfd4fd", Category.Second, Specialization.Pediatrician).setStatus(Status.Doctor));
        Doctor[] doctors = doctorService.findAll();
        PatientService patientService = new PatientServiceImpl();
        patientService.create(new Patient("Daniel", LocalDate.of(2002, 5, 2), "0502338449", "Documents", "CharNoir", "Good").setStatus(Status.Patient));
        patientService.create(new Patient("Mark", LocalDate.of(2000, 12, 18), "89444", "Bad", "Kitty", "98452").setStatus(Status.Patient));
        Patient[] patients = patientService.findAll();
        create(LocalDate.of(2021, 12, 11), LocalTime.of(8, 15), "Ну норм", 200.3d, doctors[0].getId(), patients[0].getId());
        create(LocalDate.of(2021, 12, 11), LocalTime.of(9, 45), "Ну ok", 115d, doctors[1].getId(), patients[1].getId());
    }
}
