package ua.com.alevel.hw_9_web_jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class Hw9WebJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(Hw9WebJpaApplication.class, args);
    }

}
