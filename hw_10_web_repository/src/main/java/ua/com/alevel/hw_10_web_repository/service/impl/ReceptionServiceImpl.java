package ua.com.alevel.hw_10_web_repository.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.hw_10_web_repository.exception.EntityNotFoundException;
import ua.com.alevel.hw_10_web_repository.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_10_web_repository.persistence.datatable.DataTableResponse;
import ua.com.alevel.hw_10_web_repository.persistence.entity.Doctor;
import ua.com.alevel.hw_10_web_repository.persistence.entity.Reception;
import ua.com.alevel.hw_10_web_repository.service.ReceptionService;
import ua.com.alevel.hw_10_web_repository.persistence.dao.ReceptionDao;

import java.util.Optional;

@Service
public class ReceptionServiceImpl implements ReceptionService {

    private final ReceptionDao receptionDao;

    public ReceptionServiceImpl(ReceptionDao receptionDao) {
        this.receptionDao = receptionDao;
    }

    @Override
    public void create(Reception entity) {
        receptionDao.create(entity);
    }

    @Override
    public void update(Reception entity) {
        Optional<Reception> doctor = receptionDao.findById(entity.getId());
        if (doctor.isEmpty()) {
            throw new EntityNotFoundException("There are no receptions with this id!");
        }
        receptionDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        Optional<Reception> doctorOpt = receptionDao.findById(id);
        if (doctorOpt.isEmpty()) {
            throw new EntityNotFoundException("There are no doctors with this id!");
        }
        receptionDao.delete(id);
    }

    @Override
    public Reception findById(Long id) {
        Optional<Reception> entity = receptionDao.findById(id);
        if (entity.isEmpty()) {
            throw new EntityNotFoundException("There are no doctors with this id!");
        }
        return entity.get();
    }

    @Override
    public DataTableResponse<Reception> findAll(DataTableRequest request) {
        return receptionDao.findAll(request);
    }


    @Override
    public DataTableResponse<Reception> findAllByPatientId(DataTableRequest dataTableRequest, Long id) {
        return receptionDao.findAllByPatientId(dataTableRequest, id);
    }

    @Override
    public DataTableResponse<Reception> findAllByDoctorId(DataTableRequest dataTableRequest, Long id) {
        return receptionDao.findAllByDoctorId(dataTableRequest, id);
    }
}
