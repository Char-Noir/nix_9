package ua.com.alevel.db.impl;

import ua.com.alevel.db.PatientDB;
import ua.com.alevel.entity.Patient;
import ua.com.alevel.fileoperators.FileDBOperator;
import ua.com.alevel.fileoperators.impl.FileDBOperatorImpl;
import ua.com.alevel.interfaces.DoubleLinkedList;

public class PatientDBImpl implements PatientDB {

    private final DoubleLinkedList<Patient> patients;
    private final FileDBOperator<Patient> fileDBOperator = new FileDBOperatorImpl<>(Patient.class);

    public PatientDBImpl() {
        patients = fileDBOperator.readFromFile();
    }


    public void create(Patient patient) {
        if (patient.getId() == null) {
            throw new RuntimeException("This object need to have id before creating");
        }
        patients.add(patient);
        write();
    }


    public boolean update(Patient patient) {
        boolean b = patients.update(patient);
        write();
        return b;
    }

    public boolean delete(Long id) {
        return patients.remove(id);

    }

    @Override
    public boolean exist(Long id) {
        Patient patient = patients.get(id);
        return patient != null;
    }

    public Patient findById(Long id) {
        Patient patient = patients.get(id);
        if (patient == null) {
            throw new RuntimeException("Patient not found");
        }
        return patient;
    }

    public Patient[] findAll() {
        return patients.toArray();
    }

    private void write() {
        fileDBOperator.writeToFile(patients);
    }
}
