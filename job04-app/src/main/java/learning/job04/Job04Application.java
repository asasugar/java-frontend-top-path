package learning.job04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Job04Application {

    private static final Logger log = LoggerFactory.getLogger(Job04Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Job04Application.class, args);
        log.info("Job04Application started");
    }
}
