package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.impl.DoctorDAOImpl;
import ua.com.alevel.entity.Category;
import ua.com.alevel.entity.Doctor;
import ua.com.alevel.entity.Specialization;
import ua.com.alevel.entity.Status;
import ua.com.alevel.service.DoctorService;

import java.time.LocalDate;

public class DoctorServiceImpl implements DoctorService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final DoctorDAOImpl doctorDAO = new DoctorDAOImpl();

    public void create(Doctor doctor) {
        LOGGER_INFO.info("entity Doctor create started");
        doctorDAO.create(doctor);
        LOGGER_INFO.info("entity Doctor create finished, id: " + doctor.getId());
    }

    public boolean update(Doctor doctor) {
        try {
            LOGGER_INFO.info("entity Doctor update started");
            boolean isOk = doctorDAO.update(doctor);
            LOGGER_INFO.info("entity Doctor update finished, id: " + doctor.getId());
            return isOk;
        } catch (RuntimeException e) {
            LOGGER_ERROR.error("entity Doctor not updated " + doctor.getId() + ", exception = " + e.getMessage());
            throw e;
        }
    }

    public boolean delete(Long id) {
        try {
            LOGGER_INFO.info("entity Doctor delete started");
            Doctor doctor = new Doctor()
                    .setName("Deleted")
                    .setAssessmentOfCertification(0d)
                    .setDoctorNote(" ")
                    .setDateOfCertification(LocalDate.of(2000, 1, 1))
                    .setCategory(Category.Second)
                    .setSpecialization(Specialization.Therapist)
                    .setLogin("Deleted")
                    .setHashPassword("Deleted")
                    .setStatus(Status.Deleted);
            doctor.setId(id);
            boolean isOk = doctorDAO.update(doctor);
            LOGGER_INFO.info("entity Doctor delete finished, id: " + doctor.getId());
            return isOk;
        } catch (Exception e) {
            LOGGER_ERROR.error("entity Doctor not deleted " + id + ", exception = " + e.getMessage());
            throw e;
        }
    }

    public Doctor findById(Long id) {
        try {
            return doctorDAO.findById(id);
        } catch (Exception e) {
            LOGGER_ERROR.error("entity Doctor not found " + id + ", exception = " + e.getMessage());
            throw e;
        }
    }

    public Doctor[] findAll() {
        try {
            return doctorDAO.findAll();
        } catch (Exception e) {
            LOGGER_ERROR.error("no such doctors, exception = " + e.getMessage());
            throw e;
        }
    }
}
