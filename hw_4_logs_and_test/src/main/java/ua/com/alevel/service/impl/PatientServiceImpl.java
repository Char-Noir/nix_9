package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.impl.PatientDAOImpl;
import ua.com.alevel.entity.Patient;
import ua.com.alevel.entity.Status;
import ua.com.alevel.service.PatientService;

import java.time.LocalDate;

public class PatientServiceImpl implements PatientService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final PatientDAOImpl patientDao = PatientDAOImpl.getInstance();

    public void create(Patient patient) {
        LOGGER_INFO.info("entity Patient create started");
        patientDao.create(patient);
        LOGGER_INFO.info("entity Patient create finished, id: " + patient.getId());
    }

    public boolean update(Patient patient) {
        try {
            LOGGER_INFO.info("entity Patient update started");
            boolean isOk = patientDao.update(patient);
            LOGGER_INFO.info("entity Patient update finished, id: " + patient.getId());
            return isOk;
        } catch (RuntimeException e) {
            LOGGER_ERROR.error("entity Patient not updated " + patient.getId() + ", exception = " + e.getMessage());
            throw e;
        }
    }

    public boolean delete(Long id) {
        try {
            LOGGER_INFO.info("entity Patient delete started");
            Patient patient = new Patient()
                    .setName("Deleted")
                    .setDateOfBirth(LocalDate.of(2000, 1, 1))
                    .setPhoneNumber("0000000000")
                    .setUserDocuments("Deleted")
                    .setLogin("Deleted")
                    .setHashPassword("Deleted")
                    .setStatus(Status.Deleted);
            patient.setId(id);
            boolean isOk = patientDao.update(patient);
            LOGGER_INFO.info("entity Patient delete finished, id: " + patient.getId());
            return isOk;
        } catch (Exception e) {
            LOGGER_ERROR.error("entity Patient not deleted " + id + ", exception = " + e.getMessage());
            throw e;
        }
    }

    public Patient findById(Long id) {
        try {
            return patientDao.findById(id);
        } catch (Exception e) {
            LOGGER_ERROR.error("entity Patient not found " + id + ", exception = " + e.getMessage());
            throw e;
        }
    }

    public Patient[] findAll() {
        try {
            return patientDao.findAll();
        } catch (Exception e) {
            LOGGER_ERROR.error("no such patients, exception = " + e.getMessage());
            throw e;
        }
    }
}
