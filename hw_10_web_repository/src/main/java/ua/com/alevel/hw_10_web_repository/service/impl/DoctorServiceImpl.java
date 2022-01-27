package ua.com.alevel.hw_10_web_repository.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.hw_10_web_repository.exception.EntityNotFoundException;
import ua.com.alevel.hw_10_web_repository.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_10_web_repository.persistence.datatable.DataTableResponse;
import ua.com.alevel.hw_10_web_repository.persistence.entity.Doctor;
import ua.com.alevel.hw_10_web_repository.persistence.entity.Status;
import ua.com.alevel.hw_10_web_repository.service.DoctorService;
import ua.com.alevel.hw_10_web_repository.persistence.dao.DoctorDao;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorDao doctorDao;

    public DoctorServiceImpl(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    @Override
    public void create(Doctor entity) {
        doctorDao.create(entity);
    }

    @Override
    public void update(Doctor entity) {
        Optional<Doctor> doctor = doctorDao.findById(entity.getId());
        if (doctor.isEmpty()) {
            throw new EntityNotFoundException("There are no doctors with this id!");
        }
        doctorDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        Optional<Doctor> doctorOpt = doctorDao.findById(id);
        if (doctorOpt.isEmpty()) {
            throw new EntityNotFoundException("There are no doctors with this id!");
        }
        Doctor doctor = doctorOpt.get();
        doctor.setStatus(Status.Deleted);
        doctor.setDoctorNote("Deleted");
        doctor.setAssessmentOfCertification(0d);
        doctor.setLogin("Deleted" + doctor.getIdDoctor());
        doctor.setName("Deleted");
        doctor.setDateOfCertification(LocalDate.EPOCH);
        doctorDao.update(doctor);
    }

    @Override
    public Doctor findById(Long id) {
        Optional<Doctor> entity = doctorDao.findById(id);
        if (entity.isEmpty()) {
            throw new EntityNotFoundException("There are no doctors with this id!");
        }
        return entity.get();
    }

    @Override
    public DataTableResponse<Doctor> findAll(DataTableRequest request) {
        return doctorDao.findAll(request);
    }


    @Override
    public Doctor findDoctorByPatientId(Long id) {
        Doctor entity = doctorDao.findDoctorByPatientId(id);
        if (entity == null) {
            entity = new Doctor(-1L);
        }
        return entity;
    }
}
