package ua.com.alevel.module3.dto;

import java.util.Date;

public abstract class ResponseDto {

    protected Long id;

    @Override
    public String toString() {
        return "ResponseDto{" +
                "id=" + id +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}