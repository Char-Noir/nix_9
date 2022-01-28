package ua.com.alevel.module3.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.module3.persistense.dao.ClientDao;
import ua.com.alevel.module3.persistense.datatable.DataTableRequest;
import ua.com.alevel.module3.persistense.datatable.DataTableResponse;
import ua.com.alevel.module3.persistense.entity.Client;
import ua.com.alevel.module3.service.ClientService;

import javax.persistence.EntityNotFoundException;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientDao patientDao;

    public ClientServiceImpl(ClientDao patientDao) {
        this.patientDao = patientDao;
    }

    @Override
    public Client findById(Long id) {
        if (patientDao.existById(id)) {
            Client entity = patientDao.findById(id);
            if (entity == null) {
                throw new EntityNotFoundException("There are no patients with this id!");
            }
            return entity;
        }
        throw new EntityNotFoundException("There are no patients with this id!");
    }

    @Override
    public DataTableResponse<Client> findAll(DataTableRequest request) {
        DataTableResponse<Client> dataTableResponse = patientDao.findAll(request);
        long count = patientDao.count();
        dataTableResponse.setItemsSize(count);
        return dataTableResponse;
    }

    @Override
    public boolean exists(Long id) {
        return patientDao.existById(id);
    }
}
