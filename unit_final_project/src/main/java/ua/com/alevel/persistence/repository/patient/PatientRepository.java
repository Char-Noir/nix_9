package ua.com.alevel.persistence.repository.patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.patient.Patient;
import ua.com.alevel.persistence.repository.BaseRepository;
import ua.com.alevel.persistence.type.RoleType;

import java.util.List;
import java.util.Optional;


@Repository
public interface PatientRepository extends BaseRepository<Patient> {
    Optional<Patient> findByUserId(Long id);
    Page<Patient> findAllByUser_RoleTypeNot(RoleType roleType, Pageable pageable);
}