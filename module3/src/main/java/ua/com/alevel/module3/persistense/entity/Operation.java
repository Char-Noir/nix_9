package ua.com.alevel.module3.persistense.entity;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "operation")
@Entity
public class Operation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idoperation", nullable = false)
    private Long id;


    @Column(name = "sum", nullable = false)
    private Float sum;

    @Column(name = "purpose", nullable = false, length = 45)
    private String purpose;

    @Column(name = "datetime", nullable = false)
    private Instant datetime;

    @ManyToOne
    @JoinColumn(name = "incomeid")
    private Account in;

    @ManyToOne
    @JoinColumn(name = "outcomeid")
    private Account out;


    public Instant getDatetime() {
        return datetime;
    }

    public void setDatetime(Instant datetime) {
        this.datetime = datetime;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Float getSum() {
        return sum;
    }

    public void setSum(Float sum) {
        this.sum = sum;
    }

    public Account getIn() {
        return in;
    }

    public Operation setIn(Account in) {
        this.in = in;
        return this;
    }

    public Account getOut() {
        return out;
    }

    public Operation setOut(Account out) {
        this.out = out;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}