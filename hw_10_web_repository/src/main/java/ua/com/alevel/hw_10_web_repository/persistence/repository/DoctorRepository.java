package ua.com.alevel.hw_10_web_repository.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.alevel.hw_10_web_repository.persistence.entity.Doctor;

import java.util.List;

public interface DoctorRepository extends AbstractRepository<Doctor> {

    @Query("select d from Doctor d left join DoctorPatient dp on dp.doctor = d where dp.patient.idPatient =:id order by dp.createDate")
    List<Doctor> findAllByPatientIdOrderByCreateDate(@Param("id") Long patientId);
}
