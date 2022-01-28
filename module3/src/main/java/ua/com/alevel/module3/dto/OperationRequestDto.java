package ua.com.alevel.module3.dto;

import ua.com.alevel.module3.persistense.entity.Account;
import ua.com.alevel.module3.persistense.entity.Operation;

import java.time.Instant;

public class OperationRequestDto extends RequestDto {
    Long incomeid;
    Long outcomeid;
    Float sum;
    String purpose;
    Instant datetime;

    public Operation getOperation() {
        Operation operation = new Operation();
        operation.setDatetime(datetime);
        operation.setPurpose(purpose);
        operation.setSum(sum);
        operation.setOut(new Account(outcomeid));
        operation.setIn(new Account(incomeid));
        return operation;
    }

    public Long getIncomeid() {
        return incomeid;
    }

    public OperationRequestDto setIncomeid(Long incomeid) {
        this.incomeid = incomeid;
        return this;
    }

    public Long getOutcomeid() {
        return outcomeid;
    }

    public OperationRequestDto setOutcomeid(Long outcomeid) {
        this.outcomeid = outcomeid;
        return this;
    }

    public Float getSum() {
        return sum;
    }

    public OperationRequestDto setSum(Float sum) {
        this.sum = sum;
        return this;
    }

    public String getPurpose() {
        return purpose;
    }

    public OperationRequestDto setPurpose(String purpose) {
        this.purpose = purpose;
        return this;
    }

    public Instant getDatetime() {
        return datetime;
    }

    public OperationRequestDto setDatetime(Instant datetime) {
        this.datetime = datetime;
        return this;
    }
}
