package io.github.glebchanskiy.smsbot.adb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.vidstige.jadb.JadbConnection;

@Configuration
public class JadbConfig {
    @Bean
    JadbConnection jadb() {
        return new JadbConnection();
    }
}
