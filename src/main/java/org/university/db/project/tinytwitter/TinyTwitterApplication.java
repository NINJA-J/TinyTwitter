package org.university.db.project.tinytwitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * Main Class
 */
@SpringBootApplication
@PropertySource("classpath:application.properties")
//@ComponentScan(basePackages = {"org.university.db.project.tinytwitter"})
public class TinyTwitterApplication {
    public static void main(String[] args) {
        SpringApplication.run(TinyTwitterApplication.class, args);
    }
}
