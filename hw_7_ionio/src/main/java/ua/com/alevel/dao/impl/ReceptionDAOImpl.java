package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.ReceptionDAO;
import ua.com.alevel.db.ReceptionDB;
import ua.com.alevel.db.impl.ReceptionDBImpl;
import ua.com.alevel.entity.Doctor;
import ua.com.alevel.entity.Patient;
import ua.com.alevel.entity.Reception;

import java.util.UUID;

public class ReceptionDAOImpl implements ReceptionDAO {

    private static final ReceptionDB db = new ReceptionDBImpl();

    public void create(Reception reception) {
        reception.setId(generateId());
        db.create(reception);
    }


    public boolean update(Reception reception) {
        if (!db.exist(reception.getId())) {
            throw new RuntimeException("Reception not found");
        }
        return db.update(reception);
    }

    public boolean delete(Long id) {
        if (!db.exist(id)) {
            throw new RuntimeException("Reception not found");
        }
        return db.delete(id);

    }

    public Reception findById(Long id) {
        if (!db.exist(id)) {
            throw new RuntimeException("Reception not found");
        }
        return db.findById(id);
    }

    public Reception[] findAll() {
        return db.findAll();
    }

    private long generateId() {
        Long id;
        do {
            id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;//generate positive random long values
        } while (db.exist(id));
        return id;
    }

    @Override
    public Reception[] findByPatient(Patient patient) {
        return db.findByPatient(patient);
    }

    @Override
    public Reception[] findByDoctor(Doctor doctor) {
        return db.findByDoctor(doctor);
    }
}
