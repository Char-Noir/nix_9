package ua.com.alevel.persistence.entity.doctor;

import ua.com.alevel.persistence.entity.BaseEntity;

import javax.persistence.*;

@Table(name = "category")
@Entity
public class Category extends BaseEntity implements Types {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category", nullable = false)
    private Long id;

    @Column(name = "category_name", nullable = false, length = 100)
    private String categoryName;

    public Category(Long category) {
        super();
        this.id = category;
    }

    public Category() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return getCategoryName();
    }

    @Override
    public void setName(String name) {
        setCategoryName(name);
    }
}