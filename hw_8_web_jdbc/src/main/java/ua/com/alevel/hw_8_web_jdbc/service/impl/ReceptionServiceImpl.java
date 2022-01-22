package ua.com.alevel.hw_8_web_jdbc.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.hw_8_web_jdbc.exception.EntityNotFoundException;
import ua.com.alevel.hw_8_web_jdbc.persistence.dao.ReceptionDao;
import ua.com.alevel.hw_8_web_jdbc.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_8_web_jdbc.persistence.datatable.DataTableResponse;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Reception;
import ua.com.alevel.hw_8_web_jdbc.service.ReceptionService;

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
        if (receptionDao.existById(entity.getId())) {
            receptionDao.update(entity);
        } else {
            throw new EntityNotFoundException("There are no receptions with this id!");
        }
    }

    @Override
    public void delete(Long id) {
        if (receptionDao.existById(id)) {
            receptionDao.delete(id);
        } else {
            throw new EntityNotFoundException("There are no receptions with this id!");
        }
    }

    @Override
    public Reception findById(Long id) {
        if (receptionDao.existById(id)) {
            Reception entity = receptionDao.findById(id);
            if (entity == null) {
                throw new EntityNotFoundException("There are no receptions with this id!");
            }
            return entity;
        }
        throw new EntityNotFoundException("There are no receptions with this id!");
    }

    @Override
    public DataTableResponse<Reception> findAll(DataTableRequest request) {
        DataTableResponse<Reception> dataTableResponse = receptionDao.findAll(request);
        long count = receptionDao.count();
        dataTableResponse.setItemsSize(count);
        return dataTableResponse;
    }

    @Override
    public boolean exists(Long id) {
        return receptionDao.existById(id);
    }

    @Override
    public DataTableResponse<Reception> findAllByPatientId(DataTableRequest dataTableRequest, Long id) {
        DataTableResponse<Reception> dataTableResponse = receptionDao.findAllByPatientId(dataTableRequest, id);
        long count = receptionDao.countByPatient(id);
        dataTableResponse.setItemsSize(count);
        return dataTableResponse;
    }

    @Override
    public DataTableResponse<Reception> findAllByDoctorId(DataTableRequest dataTableRequest, Long id) {
        DataTableResponse<Reception> dataTableResponse = receptionDao.findAllByDoctorId(dataTableRequest, id);
        long count = receptionDao.countByDoctor(id);
        dataTableResponse.setItemsSize(count);
        return dataTableResponse;
    }
}
