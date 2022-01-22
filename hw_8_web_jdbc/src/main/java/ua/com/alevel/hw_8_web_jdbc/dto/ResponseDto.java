package ua.com.alevel.hw_8_web_jdbc.dto;

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