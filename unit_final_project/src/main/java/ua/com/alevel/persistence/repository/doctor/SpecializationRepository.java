package ua.com.alevel.persistence.repository.doctor;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.doctor.Specialization;
import ua.com.alevel.persistence.repository.BaseRepository;


@Repository
public interface SpecializationRepository extends BaseRepository<Specialization> {

}