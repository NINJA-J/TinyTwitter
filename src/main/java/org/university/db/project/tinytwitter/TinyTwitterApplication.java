package org.university.db.project.tinytwitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * Main Class
 */
@SpringBootApplication
@PropertySource("classpath:application.properties")
public class TinyTwitterApplication {
    public static void main(String[] args) {
        SpringApplication.run(TinyTwitterApplication.class, args);
    }
}
