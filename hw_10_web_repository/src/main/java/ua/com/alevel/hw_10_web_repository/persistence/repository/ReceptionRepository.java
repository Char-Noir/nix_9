package ua.com.alevel.hw_10_web_repository.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.alevel.hw_10_web_repository.persistence.entity.Reception;

public interface ReceptionRepository extends AbstractRepository<Reception> {

    Page<Reception> findReceptionsByPatient_IdPatient(Long id, Pageable request);

    Page<Reception> findReceptionsByDoctor_idDoctor(Long id, Pageable request);
}
