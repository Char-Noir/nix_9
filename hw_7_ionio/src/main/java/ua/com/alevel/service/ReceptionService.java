package ua.com.alevel.service;

import ua.com.alevel.entity.Doctor;
import ua.com.alevel.entity.Patient;
import ua.com.alevel.entity.Reception;

public interface ReceptionService extends BaseService<Reception> {

    Reception[] findReceptionByPatient(Patient patient);

    Reception[] findReceptionByDoctor(Doctor doctor);
}