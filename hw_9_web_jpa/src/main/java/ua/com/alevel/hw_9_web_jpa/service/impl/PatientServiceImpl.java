package ua.com.alevel.hw_9_web_jpa.service.impl;


import org.springframework.stereotype.Service;
import ua.com.alevel.hw_9_web_jpa.exception.EntityNotFoundException;
import ua.com.alevel.hw_9_web_jpa.persistence.dao.PatientDao;
import ua.com.alevel.hw_9_web_jpa.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_9_web_jpa.persistence.datatable.DataTableResponse;
import ua.com.alevel.hw_9_web_jpa.persistence.entity.Patient;
import ua.com.alevel.hw_9_web_jpa.persistence.entity.Status;
import ua.com.alevel.hw_9_web_jpa.service.PatientService;

import java.time.LocalDate;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientDao patientDao;

    public PatientServiceImpl(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    @Override
    public void create(Patient entity) {
        patientDao.create(entity);
    }

    @Override
    public void update(Patient entity) {
        if (patientDao.existById(entity.getIdPatient())) {
            patientDao.update(entity);
        } else {
            throw new EntityNotFoundException("There are no patients with this id!");
        }
    }

    @Override
    public void delete(Long id) {
        if (patientDao.existById(id)) {
            Patient patient = patientDao.findById(id);
            patient.setName("DELETED");
            patient.setLogin("DELETED" + patient.getIdPatient());
            patient.setDateOfBirth(LocalDate.EPOCH);
            patient.setPhoneNumber("0000000000");
            patient.setStatus(Status.Deleted);
            patient.setUserDocuments("Deleted");
            patientDao.update(patient);
        } else {
            throw new EntityNotFoundException("There are no patients with this id!");
        }
    }

    @Override
    public Patient findById(Long id) {
        if (patientDao.existById(id)) {
            Patient entity = patientDao.findById(id);
            if (entity == null) {
                throw new EntityNotFoundException("There are no patients with this id!");
            }
            return entity;
        }
        throw new EntityNotFoundException("There are no patients with this id!");
    }

    @Override
    public DataTableResponse<Patient> findAll(DataTableRequest request) {
        DataTableResponse<Patient> dataTableResponse = patientDao.findAll(request);
        long count = patientDao.count();
        dataTableResponse.setItemsSize(count);
        return dataTableResponse;
    }

    @Override
    public boolean exists(Long id) {
        return patientDao.existById(id);
    }


}