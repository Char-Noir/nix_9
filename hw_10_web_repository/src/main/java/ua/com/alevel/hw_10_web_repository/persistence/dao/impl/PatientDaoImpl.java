package ua.com.alevel.hw_10_web_repository.persistence.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.hw_10_web_repository.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.hw_10_web_repository.persistence.dao.PatientDao;
import ua.com.alevel.hw_10_web_repository.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_10_web_repository.persistence.datatable.DataTableResponse;
import ua.com.alevel.hw_10_web_repository.persistence.entity.Patient;
import ua.com.alevel.hw_10_web_repository.persistence.repository.PatientRepository;

import java.util.Optional;

@Service
public class PatientDaoImpl implements PatientDao {

    private final CrudRepositoryHelper<Patient, PatientRepository> repositoryHelper;
    private final PatientRepository patientRepository;

    public PatientDaoImpl(CrudRepositoryHelper<Patient, PatientRepository> repositoryHelper, PatientRepository patientRepository) {
        this.repositoryHelper = repositoryHelper;
        this.patientRepository = patientRepository;
    }


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.NEVER)
    public void create(Patient entity) {
        repositoryHelper.create(patientRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(Patient entity) {
        repositoryHelper.checkExist(patientRepository, entity.getIdPatient());
        patientRepository.save(entity);
    }


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void delete(Long id) {
        repositoryHelper.delete(patientRepository, id);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Patient> findById(Long id) {
        return repositoryHelper.findById(patientRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Patient> findAll(DataTableRequest request) {
        return repositoryHelper.findAll(patientRepository, request, Patient.class);
    }


}