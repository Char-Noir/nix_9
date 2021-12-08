package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.DoctorDAO;
import ua.com.alevel.db.impl.DoctorDBImpl;
import ua.com.alevel.entity.Doctor;

import java.util.UUID;

public class DoctorDAOImpl implements DoctorDAO {

    private static final DoctorDBImpl db = new DoctorDBImpl();

    public void create(Doctor doctor) {
        doctor.setId(generateId());
        db.create(doctor);
    }


    public boolean update(Doctor doctor) {
        if (!db.exist(doctor.getId())) {
            throw new RuntimeException("Doctor not found");
        }
        return db.update(doctor);
    }

    public boolean delete(Long id) {
        if (!db.exist(id)) {
            throw new RuntimeException("Doctor not found");
        }
        return db.delete(id);

    }

    public Doctor findById(Long id) {
        if (!db.exist(id)) {
            throw new RuntimeException("Doctor not found");
        }
        return db.findById(id);
    }

    public Doctor[] findAll() {
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
