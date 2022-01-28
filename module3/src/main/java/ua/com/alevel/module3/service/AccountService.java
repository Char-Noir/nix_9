package ua.com.alevel.module3.service;

import ua.com.alevel.module3.persistense.datatable.DataTableRequest;
import ua.com.alevel.module3.persistense.datatable.DataTableResponse;
import ua.com.alevel.module3.persistense.entity.Account;

public interface AccountService extends BaseService<Account> {
    DataTableResponse<Account> findAllByClient(DataTableRequest dataTableRequest, Long id);
}
