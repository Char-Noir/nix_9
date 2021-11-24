package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.DoctorDAO;
import ua.com.alevel.entity.Doctor;
import ua.com.alevel.implementations.DoubleLinkedListImpl;
import ua.com.alevel.interfaces.DoubleLinkedList;

import java.util.UUID;

public class DoctorDAOImpl implements DoctorDAO {

    private static DoctorDAOImpl instance;
    private final DoubleLinkedList<Doctor> doctors;

    public DoctorDAOImpl() {
        doctors = new DoubleLinkedListImpl<>(Doctor.class);
    }

    public static DoctorDAOImpl getInstance() {
        if (instance == null) {
            instance = new DoctorDAOImpl();
        }
        return instance;
    }

    public void create(Doctor doctor) {
        doctor.setId(generateId());
        doctors.add(doctor);
    }


    public boolean update(Doctor doctor) {
        return doctors.update(doctor);
    }

    public boolean delete(Long id) {
        return doctors.remove(id);

    }

    public Doctor findById(Long id) {
        Doctor doctor = doctors.get(id);
        if (doctor == null) {
            throw new RuntimeException("Doctor not found");
        } else {
            return doctor;
        }
    }

    public Doctor[] findAll() {
        return doctors.toArray();
    }

    private long generateId() {
        long id;
        do {
            id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;//generate positive random long values
        } while (doctors.contains(id));
        return id;
    }
}
