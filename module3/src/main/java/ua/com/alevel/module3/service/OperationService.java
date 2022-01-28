package ua.com.alevel.module3.service;

import ua.com.alevel.module3.persistense.datatable.DataTableRequest;
import ua.com.alevel.module3.persistense.datatable.DataTableResponse;
import ua.com.alevel.module3.persistense.entity.Operation;

public interface OperationService extends BaseService<Operation> {

    void create(Operation entity);

    void update(Operation entity);

    void delete(Long id);

    DataTableResponse<Operation> findAllByAccount(DataTableRequest dataTableRequest, Long id);
}
