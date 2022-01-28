package ua.com.alevel.module3.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.module3.persistense.dao.AccountDao;
import ua.com.alevel.module3.persistense.dao.OperationDao;
import ua.com.alevel.module3.persistense.datatable.DataTableRequest;
import ua.com.alevel.module3.persistense.datatable.DataTableResponse;
import ua.com.alevel.module3.persistense.entity.Operation;
import ua.com.alevel.module3.service.OperationService;

import java.time.Instant;

@Service
public class OperationServiceImpl implements OperationService {

    final OperationDao operationDao;
    final AccountDao accountDao;

    public OperationServiceImpl(OperationDao operationDao, AccountDao accountDao) {
        this.operationDao = operationDao;
        this.accountDao = accountDao;
    }

    @Override
    public Operation findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse<Operation> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public boolean exists(Long id) {
        return false;
    }

    @Override
    @Transactional
    public void create(Operation entity) {
        entity.setDatetime(Instant.now());
        if (entity.getSum() < 0.01) {
            throw new RuntimeException("Sum must be more then 0");
        }
        operationDao.create(entity);
        accountDao.updateBalance(entity.getIn().getId(), entity.getSum());
        accountDao.updateBalance(entity.getOut().getId(), -entity.getSum());
    }

    @Override
    public void update(Operation entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public DataTableResponse<Operation> findAllByAccount(DataTableRequest dataTableRequest, Long id) {
        DataTableResponse<Operation> dataTableResponse = operationDao.findAllByAccount(dataTableRequest,id);
        long count = operationDao.countByAccount(id);
        dataTableResponse.setItemsSize(count);
        return dataTableResponse;
    }
}
