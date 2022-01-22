package ua.com.alevel.hw_8_web_jdbc.config.jpa;

import java.sql.Connection;
import java.sql.Statement;

public interface JpaConfig {

    void connect();

    Connection getConnection();

    Statement getStatement();
}