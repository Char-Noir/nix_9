package ua.com.alevel.module3.dto;

import ua.com.alevel.module3.persistense.entity.Account;

public class AccountResponseDto extends ResponseDto {

    Float balance;
    ClientResponceDto client;

    public AccountResponseDto(Account in) {
        this.balance = in.getBalance();
        setId(in.getId());
        this.client = new ClientResponceDto(in.getIdclient());
    }

    public Float getBalance() {
        return balance;
    }

    public AccountResponseDto setBalance(Float balance) {
        this.balance = balance;
        return this;
    }

    public ClientResponceDto getClient() {
        return client;
    }

    public AccountResponseDto setClient(ClientResponceDto client) {
        this.client = client;
        return this;
    }
}
