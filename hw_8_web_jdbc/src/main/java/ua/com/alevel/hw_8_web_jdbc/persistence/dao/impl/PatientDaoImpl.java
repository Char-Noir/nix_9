package ua.com.alevel.hw_8_web_jdbc.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.hw_8_web_jdbc.config.jpa.JpaConfig;
import ua.com.alevel.hw_8_web_jdbc.exception.EntityNotFoundException;
import ua.com.alevel.hw_8_web_jdbc.exception.PageNotExistException;
import ua.com.alevel.hw_8_web_jdbc.persistence.dao.PatientDao;
import ua.com.alevel.hw_8_web_jdbc.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_8_web_jdbc.persistence.datatable.DataTableResponse;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Patient;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Status;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@Service
public class PatientDaoImpl implements PatientDao {


    private final JpaConfig jpaConfig;


    public PatientDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Patient entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement("insert into patient values(default,?,?,?,?,0,?,?,(select id_status from status where status_name = 'patient'))")) {
            preparePatientStatement(entity, preparedStatement);
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void update(Patient entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement("update  patient set name =  ?, date_of_birth = ?, phone_number = ?, user_documents = ?, login = ?, hash_password = ?, id_status = (select id_status from status where status_name=?)  where id_patient = " + entity.getId())) {
            preparedStatement.setString(7, entity.getStatus().name().toLowerCase(Locale.ROOT));
            preparePatientStatement(entity, preparedStatement);
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void preparePatientStatement(Patient entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setDate(2, java.sql.Date.valueOf(entity.getDateOfBirth()));
        preparedStatement.setString(3, entity.getPhoneNumber());
        preparedStatement.setString(4, entity.getUserDocuments());
        preparedStatement.setString(5, entity.getLogin());
        preparedStatement.setString(6, entity.getHashPassword());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement("delete from  patient  where id_patient = " + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Long id) {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery("select count(*) as count from patient where id_patient=" + id)) {
            if (resultSet.next()) {
                return resultSet.getLong("count") == 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Patient findById(Long id) {
        String sql = "select *, s.status_name as status from patient p left join status s on p.id_status = s.id_status where p.id_patient = " + id;
        System.out.println("sql = " + sql);
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            if (resultSet.next()) {
                return convertResultSetToPatientFull(resultSet);
            } else {
                throw new EntityNotFoundException("There are no patients with this id!");
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return null;
    }

    @Override
    public DataTableResponse<Patient> findAll(DataTableRequest request) {
        List<Patient> patients = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        int limit = (request.getCurrentPage() - 1) * request.getPageSize();

        String sql = "select p.id_patient, p.name, p.date_of_birth, p.phone_number, s.status_name as status, count(distinct r.id_reception) as rec_count from patient p join status s on p.id_status = s.id_status left join reception r on p.id_patient = r.id_patient group by p.id_patient order by " +
                request.getSort() + " " +
                request.getOrder() + ((request.getPageSize() == -1) ? ("") : (" limit " + limit + "," + request.getPageSize()));

        System.out.println("sql = " + sql);

        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            System.out.println(resultSet.next());
            while (resultSet.next()) {
                PatientResultSet authorResultSet = convertResultSetToPatientCut(resultSet);
                patients.add(authorResultSet.getPatient());
                otherParamMap.put(authorResultSet.getPatient().getId(), authorResultSet.getRecCount());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (patients.isEmpty() && request.getCurrentPage() != 0) {
            Map<String, String> params = new HashMap<>();
            params.put("who", "patients");
            params.put("size", String.valueOf(request.getPageSize()));
            params.put("sort", request.getSort());
            params.put("order", request.getOrder());
            throw new PageNotExistException("The page you are looking for was not found", params);
        }
        DataTableResponse<Patient> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(patients);
        tableResponse.setOtherParamMap(otherParamMap);
        return tableResponse;
    }

    @Override
    public long count() {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery("select count(*) as count from patient")) {
            if (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private PatientResultSet convertResultSetToPatientCut(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("p.id_patient");
        String name = resultSet.getString("p.name");
        Timestamp dateOfBirth = resultSet.getTimestamp("p.date_of_birth");
        String phoneNumber = resultSet.getString("p.phone_number");
        String status = resultSet.getString("status");
        int recCount = resultSet.getInt("rec_count");
        Patient patient = new Patient()
                .setName(name)
                .setDateOfBirth(dateOfBirth.toLocalDateTime().toLocalDate())
                .setPhoneNumber(phoneNumber)
                .setStatus(Status.getStatusByName(status));
        patient.setId(id);
        return new PatientResultSet(patient, recCount);
    }

    private Patient convertResultSetToPatientFull(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("p.id_patient");
        String name = resultSet.getString("p.name");
        Timestamp dateOfBirth = resultSet.getTimestamp("p.date_of_birth");
        String phoneNumber = resultSet.getString("p.phone_number");
        String login = resultSet.getString("p.login");
        String userDocuments = resultSet.getString("p.user_documents");
        String status = resultSet.getString("status");
        String password = resultSet.getString("p.hash_password");
        Patient patient = new Patient()
                .setName(name)
                .setDateOfBirth(dateOfBirth.toLocalDateTime().toLocalDate())
                .setPhoneNumber(phoneNumber)
                .setStatus(Status.getStatusByName(status))
                .setLogin(login)
                .setUserDocuments(userDocuments)
                .setHashPassword(password);
        patient.setId(id);
        return patient;
    }

    @Override
    public DataTableResponse<Patient> findAllByDoctorId(DataTableRequest request, Long id) {
        List<Patient> patients = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        int limit = (request.getCurrentPage() - 1) * request.getPageSize();

        String sql = "select p.id_patient, p.name, p.date_of_birth, p.phone_number, s.status_name as status, count(distinct r.id_reception) as rec_count from patient p join status s on p.id_status = s.id_status left join reception r on p.id_patient = r.id_patient left join doctor_patient dp on p.id_patient = dp.id_patient where dp.id_doctor = " + id + " group by p.id_patient order by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();

        System.out.println("sql = " + sql);

        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                PatientResultSet authorResultSet = convertResultSetToPatientCut(resultSet);
                patients.add(authorResultSet.getPatient());
                otherParamMap.put(authorResultSet.getPatient().getId(), authorResultSet.getRecCount());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (patients.isEmpty() && request.getCurrentPage() != 0) {
            Map<String, String> params = new HashMap<>();
            params.put("who", "patients");
            params.put("size", String.valueOf(request.getPageSize()));
            params.put("sort", request.getSort());
            params.put("order", request.getOrder());
            throw new PageNotExistException("The page you are looking for was not found", params);
        }
        DataTableResponse<Patient> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(patients);
        tableResponse.setOtherParamMap(otherParamMap);
        return tableResponse;
    }


    private record PatientResultSet(Patient patient, int recCount) {

        public Patient getPatient() {
            return patient;
        }

        public int getRecCount() {
            return recCount;
        }
    }

}