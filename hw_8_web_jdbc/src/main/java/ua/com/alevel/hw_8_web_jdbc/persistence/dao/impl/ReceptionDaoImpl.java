package ua.com.alevel.hw_8_web_jdbc.persistence.dao.impl;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import ua.com.alevel.hw_8_web_jdbc.config.jpa.JpaConfig;
import ua.com.alevel.hw_8_web_jdbc.exception.EntityNotFoundException;
import ua.com.alevel.hw_8_web_jdbc.exception.PageNotExistException;
import ua.com.alevel.hw_8_web_jdbc.persistence.dao.ReceptionDao;
import ua.com.alevel.hw_8_web_jdbc.persistence.datatable.DataTableRequest;
import ua.com.alevel.hw_8_web_jdbc.persistence.datatable.DataTableResponse;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Doctor;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Patient;
import ua.com.alevel.hw_8_web_jdbc.persistence.entity.Reception;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReceptionDaoImpl implements ReceptionDao {

    private final JpaConfig jpaConfig;

    public ReceptionDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Reception entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement("insert into reception values(default,?,?,?,?,?,?)")) {
            prepareReceptionStatement(entity, preparedStatement);

        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void prepareReceptionStatement(Reception entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setDate(1, Date.valueOf(entity.getDateOfReception()));
        preparedStatement.setTime(2, Time.valueOf(entity.getReceptionTime()));
        preparedStatement.setString(3, entity.getReviewComment());
        preparedStatement.setDouble(4, entity.getReceptionPrice());
        preparedStatement.setLong(5, entity.getDoctorId());
        preparedStatement.setLong(6, entity.getPatientId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void update(Reception entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement("update reception set date_of_reception = ?, reception_time = ?, review_comment =?, reception_price = ?, id_doctor = ?, id_patient =? where id_reception = " + entity.getId())) {
            prepareReceptionStatement(entity, preparedStatement);
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement("delete from  reception  where id_reception = " + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Long id) {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery("select count(*) as count from reception where id_reception=" + id)) {
            if (resultSet.next()) {
                return resultSet.getLong("count") == 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Reception findById(Long id) {
        String sql = "select * from reception r where id_reception = " + id;
        System.out.println("sql = " + sql);
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            if (resultSet.next()) {
                return convertResultSetToReceptionFull(resultSet);
            } else {
                throw new EntityNotFoundException("There are no receptions with this id!");
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return null;
    }

    private Reception convertResultSetToReceptionFull(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("r.id_reception");
        LocalDate date = resultSet.getDate("r.date_of_reception").toLocalDate();
        LocalTime time = resultSet.getTime("r.reception_time").toLocalTime();
        Double price = resultSet.getDouble("r.reception_price");
        String comment = resultSet.getString("r.review_comment");
        Long dId = resultSet.getLong("r.id_doctor");
        Long pId = resultSet.getLong("r.id_patient");
        Reception reception = new Reception()
                .setDateOfReception(date)
                .setReceptionTime(time)
                .setReceptionPrice(price)
                .setReviewComment(comment)
                .setDoctorId(dId)
                .setPatientId(pId);
        reception.setId(id);
        return reception;
    }

    @Override
    public DataTableResponse<Reception> findAll(DataTableRequest request) {
        List<Reception> receptions = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        int limit = (request.getCurrentPage() - 1) * request.getPageSize();

        String sql = "select r.id_reception, r.date_of_reception, r.reception_time, r.reception_price, d.id_doctor, d.name, p.id_patient , p.name from reception r left join doctor d on r.id_doctor = d.id_doctor left join patient p on r.id_patient = p.id_patient  order by " +
                request.getSort() + " " +
                request.getOrder() + ((request.getPageSize() == -1) ? ("") : (" limit " + limit + "," + request.getPageSize()));

        System.out.println("sql = " + sql);

        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                ReceptionResultSet authorResultSet = convertResultSetToReceptionCut(resultSet);
                receptions.add(authorResultSet.reception());
                otherParamMap.put(authorResultSet.reception.getId(), authorResultSet.pair());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (receptions.isEmpty() && request.getCurrentPage() != 0) {
            Map<String, String> params = new HashMap<>();
            params.put("who", "receptions");
            params.put("size", String.valueOf(request.getPageSize()));
            params.put("sort", request.getSort());
            params.put("order", request.getOrder());
            throw new PageNotExistException("The page you are looking for was not found", params);
        }
        System.out.println(receptions);
        DataTableResponse<Reception> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(receptions);
        tableResponse.setOtherParamMap(otherParamMap);
        return tableResponse;
    }

    @Override
    public long count() {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery("select count(*) as count from reception")) {
            if (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public DataTableResponse<Reception> findAllByPatientId(DataTableRequest request, Long id) {
        return findAllByEntityId(request, id, "id_patient");
    }

    private ReceptionResultSet convertResultSetToReceptionCut(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("r.id_reception");
        LocalDate date = resultSet.getDate("r.date_of_reception").toLocalDate();
        LocalTime time = resultSet.getTime("r.reception_time").toLocalTime();
        Double price = resultSet.getDouble("r.reception_price");
        Long dId = resultSet.getLong("d.id_doctor");
        Long pId = resultSet.getLong("p.id_patient");
        String dName = resultSet.getString("d.name");
        String pName = resultSet.getString("p.name");
        Reception reception = new Reception()
                .setDateOfReception(date)
                .setReceptionPrice(price)
                .setReceptionTime(time);
        reception.setId(id);
        Patient patient = new Patient().setName(pName);
        patient.setId(pId);
        Doctor doctor = new Doctor().setName(dName);
        doctor.setId(dId);
        return new ReceptionResultSet(reception, new MutablePair<>(doctor, patient));
    }

    @Override
    public long countByPatient(Long id) {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery("select count(*) as count from reception where id_patient = " + id)) {
            if (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public DataTableResponse<Reception> findAllByDoctorId(DataTableRequest request, Long id) {
        return findAllByEntityId(request, id, "id_doctor");
    }

    private DataTableResponse<Reception> findAllByEntityId(DataTableRequest request, Long id, String entity) {
        List<Reception> receptions = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        int limit = (request.getCurrentPage() - 1) * request.getPageSize();

        String sql = "select r.id_reception, r.date_of_reception, r.reception_time, r.reception_price, d.id_doctor, d.name, p.id_patient , p.name from reception r left join doctor d on r.id_doctor = d.id_doctor left join patient p on r.id_patient = p.id_patient where r." + entity + " = " + id + " order by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();

        System.out.println("sql = " + sql);

        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                ReceptionResultSet authorResultSet = convertResultSetToReceptionCut(resultSet);
                receptions.add(authorResultSet.reception());
                otherParamMap.put(authorResultSet.reception.getId(), authorResultSet.pair());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (receptions.isEmpty() && (request.getCurrentPage() != 0 && request.getCurrentPage() != 1)) {
            Map<String, String> params = new HashMap<>();
            params.put("who", entity.substring(3) + "_receptions");
            params.put("id", String.valueOf(id));
            params.put("size", String.valueOf(request.getPageSize()));
            params.put("sort", request.getSort());
            params.put("order", request.getOrder());
            throw new PageNotExistException("The page you are looking for was not found", params);
        }
        System.out.println(receptions);
        DataTableResponse<Reception> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(receptions);
        tableResponse.setOtherParamMap(otherParamMap);
        return tableResponse;
    }

    @Override
    public long countByDoctor(Long id) {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery("select count(*) as count from reception where id_doctor = " + id)) {
            if (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    protected record ReceptionResultSet(Reception reception, Pair<Doctor, Patient> pair) {
    }
}
