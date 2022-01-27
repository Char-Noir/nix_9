package ua.com.alevel.hw_10_web_repository.service.impl;


import org.springframework.stereotype.Service;
import ua.com.alevel.hw_10_web_repository.exception.EntityNotFoundException;
import ua.com.alevel.hw_10_web_repository.persistence.dao.PatientDao;
import ua.com.alevel.hw_10_web_repository.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_10_web_repository.persistence.datatable.DataTableResponse;
import ua.com.alevel.hw_10_web_repository.persistence.entity.Patient;
import ua.com.alevel.hw_10_web_repository.persistence.entity.Status;
import ua.com.alevel.hw_10_web_repository.service.PatientService;

import java.time.LocalDate;
import java.util.Optional;

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
        Optional<Patient> patient = patientDao.findById(entity.getId());
        if (patient.isEmpty()) {
            throw new EntityNotFoundException("There are no patients with this id!");
        }
        patientDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        Optional<Patient> patientOpt = patientDao.findById(id);
        if (patientOpt.isEmpty()) {
            throw new EntityNotFoundException("There are no patients with this id!");
        }
        Patient patient = patientOpt.get();
        patient.setName("DELETED");
        patient.setLogin("DELETED" + patient.getIdPatient());
        patient.setDateOfBirth(LocalDate.EPOCH);
        patient.setPhoneNumber("0000000000");
        patient.setStatus(Status.Deleted);
        patient.setUserDocuments("Deleted");
        patientDao.update(patient);

    }

    @Override
    public Patient findById(Long id) {
        Optional<Patient> entity = patientDao.findById(id);
        if (entity.isEmpty()) {
            throw new EntityNotFoundException("There are no patients with this id!");
        }
        return entity.get();
    }

    @Override
    public DataTableResponse<Patient> findAll(DataTableRequest request) {
        return patientDao.findAll(request);
    }

}