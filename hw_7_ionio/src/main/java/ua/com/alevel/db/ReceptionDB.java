package ua.com.alevel.db;

import ua.com.alevel.entity.Doctor;
import ua.com.alevel.entity.Patient;
import ua.com.alevel.entity.Reception;

public interface ReceptionDB extends BaseDB<Reception> {
    Reception[] findByPatient(Patient patient);

    Reception[] findByDoctor(Doctor doctor);
}
