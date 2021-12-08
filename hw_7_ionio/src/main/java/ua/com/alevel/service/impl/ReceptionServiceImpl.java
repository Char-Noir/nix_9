package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.ReceptionDAO;
import ua.com.alevel.dao.impl.ReceptionDAOImpl;
import ua.com.alevel.entity.Doctor;
import ua.com.alevel.entity.Patient;
import ua.com.alevel.entity.Reception;
import ua.com.alevel.service.ReceptionService;

public class ReceptionServiceImpl implements ReceptionService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final ReceptionDAO receptionDAO = new ReceptionDAOImpl();

    public void create(Reception reception) {
        LOGGER_INFO.info("entity Reception create started");
        receptionDAO.create(reception);
        LOGGER_INFO.info("entity Reception create finished, id: " + reception.getId());
    }

    public boolean update(Reception reception) {
        try {
            LOGGER_INFO.info("entity Reception update started");
            boolean isOk = receptionDAO.update(reception);
            LOGGER_INFO.info("entity Reception update finished, id: " + reception.getId());
            return isOk;
        } catch (RuntimeException e) {
            LOGGER_ERROR.error("entity Reception not updated " + reception.getId() + ", exception = " + e.getMessage());
            throw e;
        }
    }

    public boolean delete(Long id) {
        try {
            LOGGER_INFO.info("entity Reception delete started");
            boolean isOk = receptionDAO.delete(id);
            LOGGER_INFO.info("entity Reception delete finished, id: " + id);
            return isOk;
        } catch (RuntimeException e) {
            LOGGER_ERROR.error("entity Reception not deleted " + id + ", exception = " + e.getMessage());
            throw e;
        }
    }

    public Reception findById(Long id) {
        try {
            return receptionDAO.findById(id);
        } catch (Exception e) {
            LOGGER_ERROR.error("entity Reception not found " + id + ", exception = " + e.getMessage());
            throw e;
        }
    }

    public Reception[] findAll() {
        try {
            return receptionDAO.findAll();
        } catch (Exception e) {
            LOGGER_ERROR.error("no such receptions, exception = " + e.getMessage());
            throw e;
        }
    }


    @Override
    public Reception[] findReceptionByPatient(Patient patient) {
        try {
            return receptionDAO.findByPatient(patient);
        } catch (Exception e) {
            LOGGER_ERROR.error("no such receptions, exception = " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Reception[] findReceptionByDoctor(Doctor doctor) {
        try {
            return receptionDAO.findByDoctor(doctor);
        } catch (Exception e) {
            LOGGER_ERROR.error("no such receptions, exception = " + e.getMessage());
            throw e;
        }
    }
}
