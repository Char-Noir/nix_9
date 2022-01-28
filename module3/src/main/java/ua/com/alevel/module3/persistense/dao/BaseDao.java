package ua.com.alevel.module3.persistense.dao;

import ua.com.alevel.module3.persistense.datatable.DataTableRequest;
import ua.com.alevel.module3.persistense.datatable.DataTableResponse;
import ua.com.alevel.module3.persistense.entity.BaseEntity;


public interface BaseDao<ENTITY extends BaseEntity> {

    boolean existById(Long id);

    ENTITY findById(Long id);

    DataTableResponse<ENTITY> findAll(DataTableRequest request);

    long count();
}