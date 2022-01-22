package ua.com.alevel.hw_8_web_jdbc.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.hw_8_web_jdbc.exception.EntityNotFoundException;
import ua.com.alevel.hw_8_web_jdbc.persistence.dao.DoctorDao;
import ua.com.alevel.hw_8_web_jdbc.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_8_web_jdbc.persistence.datatable.DataTableResponse;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Doctor;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Status;
import ua.com.alevel.hw_8_web_jdbc.service.DoctorService;

import java.time.LocalDate;

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
        if (doctorDao.existById(entity.getId())) {
            doctorDao.update(entity);
        } else {
            throw new EntityNotFoundException("There are no doctors with this id!");
        }
    }

    @Override
    public void delete(Long id) {
        if (doctorDao.existById(id)) {
            Doctor doctor = doctorDao.findById(id);
            doctor.setStatus(Status.Deleted);
            doctor.setDoctorNote("Deleted");
            doctor.setAssessmentOfCertification(0d);
            doctor.setLogin("Deleted" + doctor.getId());
            doctor.setName("Deleted");
            doctor.setDateOfCertification(LocalDate.EPOCH);
            doctorDao.update(doctor);
        } else {
            throw new EntityNotFoundException("There are no doctors with this id!");
        }
    }

    @Override
    public Doctor findById(Long id) {
        if (doctorDao.existById(id)) {
            Doctor entity = doctorDao.findById(id);
            if (entity == null) {
                throw new EntityNotFoundException("There are no doctors with this id!");
            }
            return entity;
        }
        throw new EntityNotFoundException("There are no doctors with this id!");
    }

    @Override
    public DataTableResponse<Doctor> findAll(DataTableRequest request) {
        DataTableResponse<Doctor> dataTableResponse = doctorDao.findAll(request);
        long count = doctorDao.count();
        dataTableResponse.setItemsSize(count);
        return dataTableResponse;
    }

    @Override
    public boolean exists(Long id) {
        return doctorDao.existById(id);
    }

    @Override
    public Doctor findDoctorByPatientId(Long id) {
        Doctor entity = doctorDao.findDoctorByPatientId(id);
        System.out.println(entity);
        if (entity == null) {
            entity = new Doctor(-1L);
        }
        System.out.println(entity);
        return entity;
    }
}
