package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.Worker;

import java.util.Optional;

public interface BaseWorkerService<ENTITY extends BaseEntity & Worker> extends BaseCrudService<ENTITY> {
    Optional<ENTITY> findByUserId(Long id);
}
