package ua.com.alevel.db.impl;

import ua.com.alevel.db.DoctorDB;
import ua.com.alevel.entity.Doctor;
import ua.com.alevel.fileoperators.FileDBOperator;
import ua.com.alevel.fileoperators.impl.FileDBOperatorImpl;
import ua.com.alevel.interfaces.DoubleLinkedList;

public class DoctorDBImpl implements DoctorDB {

    private final DoubleLinkedList<Doctor> doctors;
    private final FileDBOperator<Doctor> fileDBOperator = new FileDBOperatorImpl<>(Doctor.class);

    public DoctorDBImpl() {
        doctors = fileDBOperator.readFromFile();
        //doctors = new DoubleLinkedListImpl<>(Doctor.class);
    }


    public void create(Doctor doctor) {
        if (doctor.getId() == null) {
            throw new RuntimeException("This object need to have id before creating");
        }
        doctors.add(doctor);
        write();
    }


    public boolean update(Doctor doctor) {
        boolean b = doctors.update(doctor);
        write();
        return b;
    }

    public boolean delete(Long id) {
        return doctors.remove(id);

    }

    @Override
    public boolean exist(Long id) {
        Doctor doctor = doctors.get(id);
        return doctor != null;
    }

    public Doctor findById(Long id) {
        Doctor doctor = doctors.get(id);
        if (doctor == null) {
            throw new RuntimeException("Doctor not found");
        }
        return doctor;
    }

    public Doctor[] findAll() {
        return doctors.toArray();
    }

    private void write() {
        fileDBOperator.writeToFile(doctors);
    }
}
