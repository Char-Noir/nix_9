package ua.com.alevel.hw_8_web_jdbc.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.hw_8_web_jdbc.config.jpa.JpaConfig;
import ua.com.alevel.hw_8_web_jdbc.exception.EntityNotFoundException;
import ua.com.alevel.hw_8_web_jdbc.exception.PageNotExistException;
import ua.com.alevel.hw_8_web_jdbc.persistence.dao.DoctorDao;
import ua.com.alevel.hw_8_web_jdbc.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_8_web_jdbc.persistence.datatable.DataTableResponse;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Category;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Doctor;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Specialization;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Status;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@Service
public class DoctorDaoImpl implements DoctorDao {

    private final JpaConfig jpaConfig;


    public DoctorDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Doctor entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement("insert into doctor values(default,?,?,?,?,?,?,(select id_status from status where status_name = 'doctor'),(select id_category from category where category_name = ?),(select id_specializations from specializations s where s.specialization_name = ?))")) {
            prepareDoctorStatement(entity, preparedStatement);
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void update(Doctor entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement("update  doctor set name =  ?, assessment_of_certification = ?, doctor_note = ?, date_of_certification = ?, login = ?, hash_password = ? , id_category =(select id_category from category where category_name = ?), id_specializations =  (select id_specializations from specializations s where s.specialization_name = ?), id_status = (select id_status from status where status_name = ?) where id_doctor = " + entity.getId())) {
            preparedStatement.setString(9, entity.getStatus().name().toLowerCase(Locale.ROOT));
            prepareDoctorStatement(entity, preparedStatement);
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void prepareDoctorStatement(Doctor entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setDouble(2, entity.getAssessmentOfCertification());
        preparedStatement.setString(3, entity.getDoctorNote());
        preparedStatement.setDate(4, java.sql.Date.valueOf(entity.getDateOfCertification()));
        preparedStatement.setString(5, entity.getLogin());
        preparedStatement.setString(6, entity.getHashPassword());
        preparedStatement.setString(7, entity.getCategory().getMysqlValue());
        preparedStatement.setString(8, entity.getSpecialization().getMysqlValue());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement("delete from  doctor  where id_doctor = " + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Long id) {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery("select count(*) as count from doctor where id_doctor=" + id)) {
            if (resultSet.next()) {
                return resultSet.getLong("count") == 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Doctor findById(Long id) {
        String sql = "select *, s2.specialization_name as specialization,c.category_name as category, s.status_name as status from doctor d left join status s on d.id_status = s.id_status left join category c on d.id_category = c.id_category left join specializations s2 on s2.id_specializations = d.id_specializations  where d.id_doctor = " + id;
        System.out.println("sql = " + sql);
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            if (resultSet.next()) {
                return convertResultSetToDoctorFull(resultSet);
            } else {
                throw new EntityNotFoundException("There are no doctors with this id!");
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return null;
    }

    @Override
    public DataTableResponse<Doctor> findAll(DataTableRequest request) {
        List<Doctor> doctors = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        int limit = (request.getCurrentPage() - 1) * request.getPageSize();

        String sql = "select d.id_doctor, d.name, d.date_of_certification, s2.specialization_name as specialization,c.category_name as category, s.status_name as status, count(distinct r.id_reception) as rec_count from doctor d join status s on d.id_status = s.id_status left join reception r on d.id_doctor = r.id_doctor left join category c on d.id_category = c.id_category left join specializations s2 on s2.id_specializations = d.id_specializations group by id_doctor order by " +
                request.getSort() + " " +
                request.getOrder() + ((request.getPageSize() == -1) ? ("") : (" limit " + limit + "," + request.getPageSize()));

        System.out.println("sql = " + sql);

        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                DoctorDaoImpl.DoctorResultSet authorResultSet = convertResultSetToDoctorCut(resultSet);
                doctors.add(authorResultSet.getDoctor());
                otherParamMap.put(authorResultSet.getDoctor().getId(), authorResultSet.getRecCount());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (doctors.isEmpty() && (request.getCurrentPage() != 0 && request.getCurrentPage() != 1)) {
            Map<String, String> params = new HashMap<>();
            params.put("who", "doctors");
            params.put("size", String.valueOf(request.getPageSize()));
            params.put("sort", request.getSort());
            params.put("order", request.getOrder());
            throw new PageNotExistException("The page you are looking for was not found", params);
        }
        DataTableResponse<Doctor> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(doctors);
        tableResponse.setOtherParamMap(otherParamMap);
        return tableResponse;
    }

    @Override
    public long count() {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery("select count(*) as count from doctor")) {
            if (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private DoctorDaoImpl.DoctorResultSet convertResultSetToDoctorCut(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("d.id_doctor");
        String name = resultSet.getString("d.name");
        Timestamp dateOfBirth = resultSet.getTimestamp("d.date_of_certification");
        String category = resultSet.getString("category");
        String specialization = resultSet.getString("specialization");
        String status = resultSet.getString("status");
        int recCount = resultSet.getInt("rec_count");
        Doctor doctor = new Doctor()
                .setName(name)
                .setDateOfCertification(dateOfBirth.toLocalDateTime().toLocalDate())
                .setCategory(Category.getCategoryByMysql(category))
                .setSpecialization(Specialization.getSpecializationByMySql(specialization))
                .setStatus(Status.getStatusByName(status));
        doctor.setId(id);
        return new DoctorDaoImpl.DoctorResultSet(doctor, recCount);
    }

    private Doctor convertResultSetToDoctorFull(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("d.id_doctor");
        String name = resultSet.getString("d.name");
        Timestamp dateOfBirth = resultSet.getTimestamp("d.date_of_certification");
        String note = resultSet.getString("d.doctor_note");
        String login = resultSet.getString("d.login");
        Double assessmentOfCertification = resultSet.getDouble("d.assessment_of_certification");
        String category = resultSet.getString("category");
        String specialization = resultSet.getString("specialization");
        String status = resultSet.getString("status");
        String password = resultSet.getString("d.hash_password");
        Doctor doctor = new Doctor()
                .setName(name)
                .setDateOfCertification(dateOfBirth.toLocalDateTime().toLocalDate())
                .setDoctorNote(note)
                .setAssessmentOfCertification(assessmentOfCertification)
                .setCategory(Category.getCategoryByMysql(category))
                .setSpecialization(Specialization.getSpecializationByMySql(specialization))
                .setStatus(Status.getStatusByName(status))
                .setLogin(login)
                .setHashPassword(password);
        doctor.setId(id);
        return doctor;
    }

    @Override
    public Doctor findDoctorByPatientId(Long id) {
        String sql = "select *, s2.specialization_name as specialization, c.category_name as category, s.status_name as status  from doctor d left join status s on d.id_status = s.id_status left join doctor_patient dp on d.id_doctor = dp.id_doctor left join category c on d.id_category = c.id_category left join specializations s2 on s2.id_specializations = d.id_specializations where dp.id_patient = " + id + " order by dp.end_date desc limit 1";
        System.out.println("sql = " + sql);
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            if (resultSet.next()) {
                return convertResultSetToDoctorFull(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return null;
    }


    private record DoctorResultSet(Doctor doctor, int recCount) {

        public Doctor getDoctor() {
            return doctor;
        }

        public int getRecCount() {
            return recCount;
        }
    }

}
