package ua.com.alevel.hw_9_web_jpa.dto;

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