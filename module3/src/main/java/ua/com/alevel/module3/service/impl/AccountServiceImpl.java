package ua.com.alevel.module3.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.module3.persistense.dao.AccountDao;
import ua.com.alevel.module3.persistense.datatable.DataTableRequest;
import ua.com.alevel.module3.persistense.datatable.DataTableResponse;
import ua.com.alevel.module3.persistense.entity.Account;
import ua.com.alevel.module3.service.AccountService;

import javax.persistence.EntityNotFoundException;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountDao patientDao;

    public AccountServiceImpl(AccountDao patientDao) {
        this.patientDao = patientDao;
    }

    @Override
    public DataTableResponse<Account> findAllByClient(DataTableRequest dataTableRequest, Long id) {
        DataTableResponse<Account> dataTableResponse = patientDao.findAllByClient(dataTableRequest,id);
        long count = patientDao.countByClient(id);
        dataTableResponse.setItemsSize(count);
        return dataTableResponse;
    }

    @Override
    public Account findById(Long id) {
        if (patientDao.existById(id)) {
            Account entity = patientDao.findById(id);
            if (entity == null) {
                throw new EntityNotFoundException("There are no patients with this id!");
            }
            return entity;
        }
        throw new EntityNotFoundException("There are no patients with this id!");
    }

    @Override
    public DataTableResponse<Account> findAll(DataTableRequest request) {
        DataTableResponse<Account> dataTableResponse = patientDao.findAll(request);
        long count = patientDao.count();
        dataTableResponse.setItemsSize(count);
        return dataTableResponse;
    }

    @Override
    public boolean exists(Long id) {
        return patientDao.existById(id);
    }
}
