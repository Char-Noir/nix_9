package ua.com.alevel.hw_10_web_repository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class Hw10WebRepositoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(Hw10WebRepositoryApplication.class, args);
    }

}
