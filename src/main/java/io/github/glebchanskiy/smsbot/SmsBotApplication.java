package io.github.glebchanskiy.smsbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmsBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmsBotApplication.class, args);
    }
}
