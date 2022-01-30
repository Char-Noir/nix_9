package ua.com.alevel.persistence.entity.patient;

import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.Worker;
import ua.com.alevel.persistence.entity.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "patient", indexes = {
        @Index(name = "patient_date_name", columnList = "name, date_of_birth"),
        @Index(name = "patient_F_name_docs", columnList = "user_documents"),
        @Index(name = "patient_id_user_uindex", columnList = "id_user", unique = true)
})
@Entity
public class Patient extends BaseEntity implements Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_patient", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "phone_number", nullable = false, length = 100)
    private String phoneNumber;

    @Lob
    @Column(name = "user_documents", nullable = false)
    private String userDocuments;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    public User getIdUser() {
        return user;
    }

    public void setIdUser(User idUser) {
        this.user = idUser;
    }

    public String getUserDocuments() {
        return userDocuments;
    }

    public void setUserDocuments(String userDocuments) {
        this.userDocuments = userDocuments;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}