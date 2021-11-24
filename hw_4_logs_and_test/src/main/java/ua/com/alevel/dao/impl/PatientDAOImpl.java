package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.PatientDAO;
import ua.com.alevel.entity.Patient;
import ua.com.alevel.entity.Status;
import ua.com.alevel.implementations.DoubleLinkedListImpl;
import ua.com.alevel.interfaces.DoubleLinkedList;

import java.time.LocalDate;
import java.util.UUID;

public class PatientDAOImpl implements PatientDAO {

    private static PatientDAOImpl instance;
    private final DoubleLinkedList<Patient> patients;

    public PatientDAOImpl() {
        patients = new DoubleLinkedListImpl<>(Patient.class);
    }

    public static PatientDAOImpl getInstance() {
        if (instance == null) {
            instance = new PatientDAOImpl();
        }
        return instance;
    }

    public void create(Patient patient) {
        patient.setId(generateId());
        patients.add(patient);
    }


    public boolean update(Patient patient) {
        return patients.update(patient);
    }

    public boolean delete(Long id) {
        return patients.remove(id);
    }

    public Patient findById(Long id) {
        Patient patient = patients.get(id);
        if (patient == null) {
            throw new RuntimeException("Patient not found");
        } else {
            return patient;
        }
    }

    public Patient[] findAll() {
        return patients.toArray();
    }

    private long generateId() {
        long id;
        do {
            id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;//generate positive random long values
        } while (patients.contains(id));
        return id;
    }
}
