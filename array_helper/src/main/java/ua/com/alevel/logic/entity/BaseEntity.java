package ua.com.alevel.logic.entity;

import java.util.Date;
import java.util.Objects;

public abstract class BaseEntity {
    protected Long id;
    protected Date created;
    protected Date updated;

    public BaseEntity(Long id) {
        this.id = id;
    }

    public BaseEntity() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }
}
