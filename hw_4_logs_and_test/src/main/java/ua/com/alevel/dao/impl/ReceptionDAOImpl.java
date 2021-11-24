package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.ReceptionDAO;
import ua.com.alevel.entity.Doctor;
import ua.com.alevel.entity.Patient;
import ua.com.alevel.entity.Reception;
import ua.com.alevel.implementations.DoubleLinkedListImpl;
import ua.com.alevel.interfaces.DoubleLinkedList;

import java.util.UUID;

public class ReceptionDAOImpl implements ReceptionDAO {

    private static ReceptionDAOImpl instance;
    private final DoubleLinkedList<Reception> receptions;

    public ReceptionDAOImpl() {
        receptions = new DoubleLinkedListImpl<>(Reception.class);
    }

    public static ReceptionDAOImpl getInstance() {
        if (instance == null) {
            instance = new ReceptionDAOImpl();
        }
        return instance;
    }

    public void create(Reception reception) {
        reception.setId(generateId());
        receptions.add(reception);
    }


    public boolean update(Reception reception) {
        return receptions.update(reception);
    }

    public boolean delete(Long id) {
        return receptions.remove(id);

    }

    public Reception findById(Long id) {
        Reception reception = receptions.get(id);
        if (reception == null) {
            throw new RuntimeException("Reception not found");
        } else {
            return reception;
        }
    }

    public Reception[] findAll() {
        return receptions.toArray();
    }

    private long generateId() {
        long id;
        do {
            id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;//generate positive random long values
        } while (receptions.contains(id));
        return id;
    }

    @Override
    public Reception[] findByPatient(Patient patient) {
        DoubleLinkedList<Reception> recByPatient = new DoubleLinkedListImpl<>(Reception.class);
        Reception[] recs = receptions.toArray();
        for (Reception rec :
                recs) {
            if (patient.getId() == rec.getPatientId()) {
                recByPatient.add(rec);
            }
        }
        return recByPatient.toArray();
    }

    @Override
    public Reception[] findByDoctor(Doctor doctor) {
        DoubleLinkedList<Reception> recByDoctor = new DoubleLinkedListImpl<>(Reception.class);
        Reception[] recs = receptions.toArray();
        for (Reception rec :
                recs) {
            if (doctor.getId() == rec.getDoctorId()) {
                recByDoctor.add(rec);
            }
        }
        return recByDoctor.toArray();
    }
}
