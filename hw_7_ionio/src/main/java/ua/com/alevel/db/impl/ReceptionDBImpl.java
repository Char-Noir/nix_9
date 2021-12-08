package ua.com.alevel.db.impl;

import ua.com.alevel.db.ReceptionDB;
import ua.com.alevel.entity.Doctor;
import ua.com.alevel.entity.Patient;
import ua.com.alevel.entity.Reception;
import ua.com.alevel.fileoperators.FileDBOperator;
import ua.com.alevel.fileoperators.impl.FileDBOperatorImpl;
import ua.com.alevel.implementations.DoubleLinkedListImpl;
import ua.com.alevel.interfaces.DoubleLinkedList;

import java.util.Objects;

public class ReceptionDBImpl implements ReceptionDB {

    private final DoubleLinkedList<Reception> receptions;
    private final FileDBOperator<Reception> fileDBOperator = new FileDBOperatorImpl<>(Reception.class);

    public ReceptionDBImpl() {
        receptions = fileDBOperator.readFromFile();
    }


    public void create(Reception reception) {
        if (reception.getId() == null) {
            throw new RuntimeException("This object need to have id before creating");
        }
        receptions.add(reception);
        write();
    }


    public boolean update(Reception reception) {
        boolean b = receptions.update(reception);
        write();
        return b;
    }

    public boolean delete(Long id) {
        return receptions.remove(id);

    }

    @Override
    public boolean exist(Long id) {
        Reception reception = receptions.get(id);
        return reception != null;
    }

    public Reception findById(Long id) {
        Reception reception = receptions.get(id);
        if (reception == null) {
            throw new RuntimeException("Reception not found");
        }
        return reception;
    }

    public Reception[] findAll() {
        return receptions.toArray();
    }

    private void write() {
        fileDBOperator.writeToFile(receptions);
    }

    @Override
    public Reception[] findByPatient(Patient patient) {
        DoubleLinkedList<Reception> recByPatient = new DoubleLinkedListImpl<>(Reception.class);
        Reception[] recs = receptions.toArray();
        for (Reception rec :
                recs) {
            if (Objects.equals(patient.getId(), rec.getPatientId())) {
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
            if (Objects.equals(doctor.getId(), rec.getDoctorId())) {
                recByDoctor.add(rec);
            }
        }
        return recByDoctor.toArray();
    }
}
