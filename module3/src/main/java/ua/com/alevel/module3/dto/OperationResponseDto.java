package ua.com.alevel.module3.dto;

import ua.com.alevel.module3.persistense.entity.Operation;

import java.time.Instant;

public class OperationResponseDto extends ResponseDto {

    AccountResponseDto income;
    AccountResponseDto outcome;

    Instant datetime;
    String purpose;
    Float sum;

    public OperationResponseDto(Operation operation) {
        this.income = new AccountResponseDto(operation.getIn());
        this.outcome = new AccountResponseDto(operation.getOut());
        this.datetime = operation.getDatetime();
        this.purpose = operation.getPurpose();
        this.sum = operation.getSum();
        setId(operation.getId());
    }

    public AccountResponseDto getIncome() {
        return income;
    }

    public OperationResponseDto setIncome(AccountResponseDto income) {
        this.income = income;
        return this;
    }

    public AccountResponseDto getOutcome() {
        return outcome;
    }

    public OperationResponseDto setOutcome(AccountResponseDto outcome) {
        this.outcome = outcome;
        return this;
    }

    public Instant getDatetime() {
        return datetime;
    }

    public OperationResponseDto setDatetime(Instant datetime) {
        this.datetime = datetime;
        return this;
    }

    public String getPurpose() {
        return purpose;
    }

    public OperationResponseDto setPurpose(String purpose) {
        this.purpose = purpose;
        return this;
    }

    public Float getSum() {
        return sum;
    }

    public OperationResponseDto setSum(Float sum) {
        this.sum = sum;
        return this;
    }
}
