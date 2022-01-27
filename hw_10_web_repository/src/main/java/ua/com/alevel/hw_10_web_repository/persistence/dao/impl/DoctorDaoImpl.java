package ua.com.alevel.hw_10_web_repository.persistence.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.hw_10_web_repository.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.hw_10_web_repository.persistence.dao.DoctorDao;
import ua.com.alevel.hw_10_web_repository.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_10_web_repository.persistence.datatable.DataTableResponse;
import ua.com.alevel.hw_10_web_repository.persistence.entity.Doctor;
import ua.com.alevel.hw_10_web_repository.persistence.repository.DoctorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorDaoImpl implements DoctorDao {

    private final CrudRepositoryHelper<Doctor, DoctorRepository> repositoryHelper;
    private final DoctorRepository doctorRepository;

    public DoctorDaoImpl(CrudRepositoryHelper<Doctor, DoctorRepository> repositoryHelper, DoctorRepository doctorRepository) {
        this.repositoryHelper = repositoryHelper;
        this.doctorRepository = doctorRepository;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.NEVER)
    public void create(Doctor entity) {
        repositoryHelper.create(doctorRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(Doctor entity) {
        repositoryHelper.checkExist(doctorRepository, entity.getIdDoctor());
        doctorRepository.save(entity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void delete(Long id) {
        repositoryHelper.delete(doctorRepository, id);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Doctor> findById(Long id) {
        return repositoryHelper.findById(doctorRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Doctor> findAll(DataTableRequest request) {
        return repositoryHelper.findAll(doctorRepository, request, Doctor.class);
    }

    @Override
    public Doctor findDoctorByPatientId(Long id) {
        List<Doctor> doctors = doctorRepository.findAllByPatientIdOrderByCreateDate(id);
        if (doctors.isEmpty()) {
            return new Doctor(-1L);
        }
        return doctors.get(0);
    }

}
