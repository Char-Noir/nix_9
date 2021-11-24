package ua.com.alevel.controller.impl;

import ua.com.alevel.console.responce.Responce;
import ua.com.alevel.controller.DoctorController;
import ua.com.alevel.entity.Category;
import ua.com.alevel.entity.Doctor;
import ua.com.alevel.entity.Specialization;
import ua.com.alevel.entity.Status;
import ua.com.alevel.service.DoctorService;
import ua.com.alevel.service.impl.DoctorServiceImpl;

import java.time.LocalDate;

public class DoctorControllerImpl implements DoctorController {

    DoctorService doctorService = new DoctorServiceImpl();

    @Override
    public Responce findAllCategories() {
        StringBuilder sb = new StringBuilder("Категории: \n");
        for (Category category : Category.values()) {
            sb.append(category.getValue()).append(", ");
        }
        sb.append("\n");
        return Responce.getOk(sb.toString());
    }

    @Override
    public Responce create(String name, Double assessmentOfCertification, String doctorNote, LocalDate dateOfCertification, String login, String hashPassword, String category, String specialization) {
        Doctor doctor;
        try {
            doctor = new Doctor()
                    .setName(name)
                    .setAssessmentOfCertification(assessmentOfCertification)
                    .setDoctorNote(doctorNote)
                    .setDateOfCertification(dateOfCertification)
                    .setCategory(Category.getCategoryByString(category))
                    .setSpecialization(Specialization.getSpecializationByString(specialization))
                    .setLogin(login)
                    .setHashPassword(hashPassword)
                    .setStatus(Status.Doctor);
            doctorService.create(doctor);
            return Responce.getRedirect(new StringBuilder("Doctor created successfully: ").append(doctor).toString(), "ShowAll/doctors");
        } catch (Exception e) {
            return Responce.getError("Error when creating doctor", e.getMessage());
        }

    }

    @Override
    public Responce update(Long id, String name, Double assessmentOfCertification, String doctorNote, LocalDate dateOfCertification, String login, String hashPassword, String category, String specialization) {
        Doctor doctor;
        try {
            doctor = new Doctor()
                    .setName(name)
                    .setAssessmentOfCertification(assessmentOfCertification)
                    .setDoctorNote(doctorNote)
                    .setDateOfCertification(dateOfCertification)
                    .setCategory(Category.getCategoryByString(category))
                    .setSpecialization(Specialization.getSpecializationByString(specialization))
                    .setLogin(login)
                    .setHashPassword(hashPassword)
                    .setStatus(Status.Doctor);
            doctor.setId(id);
            if (doctorService.update(doctor)) {
                return Responce.getRedirect("Doctor updated", "ShowAll/doctors");
            } else {
                return Responce.getError("Doctor not updated", "Doctor not found");
            }
        } catch (Exception e) {
            return Responce.getError("Doctor not updated", e.getMessage());
        }
    }

    @Override
    public Responce delete(long id) {
        try {
            if (doctorService.delete(id)) {
                return Responce.getRedirect("Doctor deleted", "ShowAll/doctors");
            } else {
                return Responce.getError("Doctor not deleted", "Doctor not found");
            }
        } catch (Exception e) {
            return Responce.getError("Doctor not deleted", e.getMessage());
        }
    }

    @Override
    public Responce findById(long id) {
        Doctor doctor;
        try {
            doctor = doctorService.findById(id);
            if (doctor != null) {
                return Responce.getOk(doctor.toString());
            } else {
                return Responce.getError("Doctor not found", "Doctor equals null");
            }
        } catch (Exception e) {
            return Responce.getError("Doctor not found", e.getMessage());
        }
    }

    @Override
    public Responce findAll() {
        Doctor[] doctors = doctorService.findAll();
        if (doctors.length == 0) {
            return Responce.getOk("No doctors in app");
        }
        StringBuilder stringBuilder = new StringBuilder("Доктора:\n");
        for (Doctor doctor :
                doctors) {
            if (doctor.getStatus() != Status.Deleted) {
                stringBuilder.append(doctor);
            } else {
                stringBuilder.append("Doctor Deleted with id: ").append(doctor.getId()).append('\n');
            }
        }
        return Responce.getOk(stringBuilder.toString());
    }

    @Override
    public Responce findAllSpecializations() {
        StringBuilder sb = new StringBuilder("Специализации: \n");
        for (Specialization specialization : Specialization.values()) {
            sb.append(specialization.getValue()).append(", ");
        }
        sb.append("\n");
        return Responce.getOk(sb.toString());
    }

    @Override
    public void initDB() {
        //
    }
}
