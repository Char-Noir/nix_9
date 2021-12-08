package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.PatientDAO;
import ua.com.alevel.db.PatientDB;
import ua.com.alevel.db.impl.PatientDBImpl;
import ua.com.alevel.entity.Patient;

import java.util.UUID;

public class PatientDAOImpl implements PatientDAO {

    private static final PatientDB db = new PatientDBImpl();

    public void create(Patient patient) {
        patient.setId(generateId());
        db.create(patient);
    }


    public boolean update(Patient patient) {
        if (!db.exist(patient.getId())) {
            throw new RuntimeException("Patient not found");
        }
        return db.update(patient);
    }

    public boolean delete(Long id) {
        if (!db.exist(id)) {
            throw new RuntimeException("Patient not found");
        }
        return db.delete(id);

    }

    public Patient findById(Long id) {
        if (!db.exist(id)) {
            throw new RuntimeException("Patient not found");
        }
        return db.findById(id);
    }

    public Patient[] findAll() {
        return db.findAll();
    }

    private long generateId() {
        Long id;
        do {
            id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;//generate positive random long values
        } while (db.exist(id));
        return id;
    }
}
