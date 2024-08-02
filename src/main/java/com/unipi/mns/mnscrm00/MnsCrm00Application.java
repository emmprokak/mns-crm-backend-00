package com.unipi.mns.mnscrm00;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MnsCrm00Application {

    public static void main(String[] args) {
        SpringApplication.run(MnsCrm00Application.class, args);
    }

}
