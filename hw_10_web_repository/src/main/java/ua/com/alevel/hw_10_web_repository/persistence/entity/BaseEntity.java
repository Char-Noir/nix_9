package ua.com.alevel.hw_10_web_repository.persistence.entity;

import javax.persistence.Transient;

public class BaseEntity {

    @Transient
    protected Long idEntity;

    public BaseEntity() {

    }

    public Long getId() {
        return idEntity;
    }

    protected BaseEntity setId(Long idEntity) {
        this.idEntity = idEntity;
        return this;
    }
}
