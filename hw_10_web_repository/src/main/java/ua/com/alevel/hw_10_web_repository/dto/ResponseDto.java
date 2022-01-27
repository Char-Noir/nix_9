package ua.com.alevel.hw_10_web_repository.dto;

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