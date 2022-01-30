package ua.com.alevel.persistence.entity.doctor;

import ua.com.alevel.persistence.entity.BaseEntity;

import javax.persistence.*;

@Table(name = "specializations")
@Entity
public class Specialization extends BaseEntity implements Types {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_specializations", nullable = false)
    private Long id;

    @Column(name = "specialization_name", nullable = false, length = 100)
    private String specializationName;

    public Specialization(Long specialization) {
        super();
        this.id = specialization;
    }

    public Specialization() {
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return getSpecializationName();
    }

    @Override
    public void setName(String name) {
        setSpecializationName(name);
    }
}