package ua.com.alevel.module3.persistense.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "client", indexes = {
        @Index(name = "email_UNIQUE", columnList = "email", unique = true)
})
@Entity
public class Client extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idclient", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @OneToMany(mappedBy = "idclient", orphanRemoval = true)
    private List<Account> accounts = new ArrayList<>();

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}