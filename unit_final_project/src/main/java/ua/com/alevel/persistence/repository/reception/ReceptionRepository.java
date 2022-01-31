package ua.com.alevel.persistence.repository.reception;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.reception.Reception;
import ua.com.alevel.persistence.repository.BaseRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReceptionRepository extends BaseRepository<Reception> {

    Page<Reception> findAllByPatient_Id(Long id, Pageable pageable);

    Page<Reception> findAllByDoctor_Id(Long id, Pageable pageable);

    List<Reception> findAllByDateOfReceptionBetween(LocalDate start, LocalDate end);
}
