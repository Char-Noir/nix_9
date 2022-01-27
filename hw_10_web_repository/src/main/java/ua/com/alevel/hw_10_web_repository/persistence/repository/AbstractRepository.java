package ua.com.alevel.hw_10_web_repository.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import ua.com.alevel.hw_10_web_repository.persistence.entity.BaseEntity;

@NoRepositoryBean
public interface AbstractRepository<E extends BaseEntity> extends JpaRepository<E, Long>, JpaSpecificationExecutor<E> { }