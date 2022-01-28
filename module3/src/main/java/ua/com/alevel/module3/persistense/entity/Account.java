package ua.com.alevel.module3.persistense.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "account", indexes = {
        @Index(name = "idclient_idx", columnList = "idclient")
})
@Entity
public class Account extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idaccount", nullable = false)
    private Long id;

    @Column(name = "balance", nullable = false)
    private Float balance;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idclient", nullable = false)
    private Client idclient;
    @OneToMany(mappedBy = "in", orphanRemoval = true)
    private List<Operation> operationsIn = new ArrayList<>();
    @OneToMany(mappedBy = "out", orphanRemoval = true)
    private List<Operation> operationsOut = new ArrayList<>();

    public Account(Long outcomeid) {
        this.id = outcomeid;
    }

    public Account() {
    }

    public List<Operation> getOperationsOut() {
        return operationsOut;
    }

    public Account setOperationsOut(List<Operation> operationsOut) {
        this.operationsOut = operationsOut;
        return this;
    }

    public Client getIdclient() {
        return idclient;
    }

    public void setIdclient(Client idclient) {
        this.idclient = idclient;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Operation> getOperationsIn() {
        return operationsIn;
    }

    public void setOperationsIn(List<Operation> operations) {
        this.operationsIn = operations;
    }


}