package ua.com.alevel.entity;

import java.util.Objects;

public abstract class BaseEntity {
    protected long id;

    public BaseEntity(long id) {
        this.id = id;
    }

    public BaseEntity() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return id == that.id;
    }
}
