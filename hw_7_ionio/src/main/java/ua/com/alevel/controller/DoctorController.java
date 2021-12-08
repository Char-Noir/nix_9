package ua.com.alevel.controller;

import ua.com.alevel.console.responce.Responce;
import ua.com.alevel.entity.Category;
import ua.com.alevel.entity.Doctor;
import ua.com.alevel.entity.Specialization;

import java.time.LocalDate;

public interface DoctorController extends BaseController<Doctor> {

    Responce findAllCategories();

    Responce create(String name, Double assessmentOfCertification, String doctorNote, LocalDate dateOfCertification, String login, String hashPassword, String category, String specialization);

    Responce update(Long id, String name, Double assessmentOfCertification, String doctorNote, LocalDate dateOfCertification, String login, String hashPassword,String category, String specialization);

    Responce delete(long id);

    Responce findById(long id);

    Responce findAll();

    Responce findAllSpecializations();
}
