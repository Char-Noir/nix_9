package ua.com.alevel.module3.dto;

import ua.com.alevel.module3.persistense.entity.Client;

public class ClientResponceDto extends ResponseDto {

    String email;

    public ClientResponceDto(Client idclient) {
        this.email = idclient.getEmail();
        setId(idclient.getId());
    }

    public String getEmail() {
        return email;
    }

    public ClientResponceDto setEmail(String email) {
        this.email = email;
        return this;
    }
}
