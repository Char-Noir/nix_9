package ua.com.alevel.hw_9_web_jpa.persistence.entity;

import ua.com.alevel.hw_9_web_jpa.persistence.converter.StatusAttributeConverter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Table(name = "patient", indexes = {
        @Index(name = "fk_Patient_Status1_idx", columnList = "id_status"),
        @Index(name = "login_UNIQUE", columnList = "login", unique = true),
        @Index(name = "patient_date_name", columnList = "name, date_of_birth"),
        @Index(name = "patient_F_name_docs", columnList = "user_documents"),
        @Index(name = "hash_password_UNIQUE", columnList = "hash_password", unique = true)
})
@Entity
public class Patient extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_patient", nullable = false)
    private Long idPatient;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "phone_number", nullable = false, length = 100)
    private String phoneNumber;

    @Lob
    @Column(name = "user_documents", nullable = false)
    private String userDocuments;


    @Column(name = "login", nullable = false, length = 100)
    private String login;

    @Column(name = "hash_password", nullable = false, length = 100)
    private String hashPassword;

    @Convert(converter = StatusAttributeConverter.class)
    @Column(name = "id_status")
    private Status status;
    @OneToMany(mappedBy = "doctor", orphanRemoval = true)
    private List<Reception> receptions = new java.util.ArrayList<>();

    public Patient() {
    }

    public Patient(Long idPatient) {
        this.idPatient = idPatient;
    }

    public List<Reception> getReceptions() {
        return receptions;
    }

    public Patient setReceptions(List<Reception> receptions) {
        this.receptions = receptions;
        return this;
    }

    public Long getIdPatient() {
        return idPatient;
    }

    public Patient setIdPatient(Long id) {
        this.idPatient = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Patient setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Patient setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Patient setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getUserDocuments() {
        return userDocuments;
    }

    public Patient setUserDocuments(String userDocuments) {
        this.userDocuments = userDocuments;
        return this;
    }


    public String getLogin() {
        return login;
    }

    public Patient setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public Patient setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public Patient setStatus(Status idStatus) {
        this.status = idStatus;
        return this;
    }
}