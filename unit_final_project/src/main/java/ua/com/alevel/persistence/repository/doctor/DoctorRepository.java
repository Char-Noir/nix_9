package ua.com.alevel.persistence.repository.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.doctor.Doctor;
import ua.com.alevel.persistence.repository.BaseRepository;
import ua.com.alevel.persistence.type.RoleType;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends BaseRepository<Doctor> {
    Optional<Doctor> findByUserId(Long id);

    Page<Doctor> findAllByUser_RoleTypeNot(RoleType roleType, Pageable pageable);
}