package ua.com.alevel.hw_9_web_jpa.persistence.dao;

import ua.com.alevel.hw_9_web_jpa.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_9_web_jpa.persistence.datatable.DataTableResponse;
import ua.com.alevel.hw_9_web_jpa.persistence.entity.BaseEntity;


public interface BaseDao<ENTITY extends BaseEntity> {

    void create(ENTITY entity);

    void update(ENTITY entity);

    void delete(Long id);

    boolean existById(Long id);

    ENTITY findById(Long id);

    DataTableResponse<ENTITY> findAll(DataTableRequest request);

    long count();
}