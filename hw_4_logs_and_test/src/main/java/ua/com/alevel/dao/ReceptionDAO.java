package ua.com.alevel.dao;

import ua.com.alevel.entity.Doctor;
import ua.com.alevel.entity.Patient;
import ua.com.alevel.entity.Reception;

public interface ReceptionDAO extends BaseDAO<Reception> {

    Reception[] findByPatient(Patient patient);

    Reception[] findByDoctor(Doctor doctor);
}