package ua.com.alevel.module3.service;

import ua.com.alevel.module3.persistense.datatable.DataTableRequest;
import ua.com.alevel.module3.persistense.datatable.DataTableResponse;
import ua.com.alevel.module3.persistense.entity.BaseEntity;


public interface BaseService<ENTITY extends BaseEntity> {

    ENTITY findById(Long id);
    DataTableResponse<ENTITY> findAll(DataTableRequest request);

    boolean exists(Long id);
}