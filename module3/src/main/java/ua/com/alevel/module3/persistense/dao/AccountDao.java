package ua.com.alevel.module3.persistense.dao;

import ua.com.alevel.module3.persistense.datatable.DataTableRequest;
import ua.com.alevel.module3.persistense.datatable.DataTableResponse;
import ua.com.alevel.module3.persistense.entity.Account;

public interface AccountDao extends BaseDao<Account> {
    DataTableResponse<Account> findAllByClient(DataTableRequest dataTableRequest,Long id);

    long countByClient(Long id);

    void updateBalance(Long id, Float balance);
}
