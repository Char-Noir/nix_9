package ua.com.alevel.hw_8_web_jdbc.service;

import ua.com.alevel.hw_8_web_jdbc.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_8_web_jdbc.persistence.datatable.DataTableResponse;
import ua.com.alevel.logic.entity.BaseEntity;

public interface BaseService<ENTITY extends BaseEntity> {

    void create(ENTITY entity);
    void update(ENTITY entity);
    void delete(Long id);
    ENTITY findById(Long id);
    DataTableResponse<ENTITY> findAll(DataTableRequest request);

    boolean exists(Long id);
}