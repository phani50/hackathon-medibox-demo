package com.hackathon.medibox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import software.amazon.awssdk.services.ses.SesClient;

@SpringBootApplication
public class HackathonMediboxDemoApplication {

    Logger logger = LoggerFactory.getLogger(HackathonMediboxDemoApplication.class);

    @Bean
    SesClient createSimpleEmailService() {
        return SesClient.builder().build();
    }

    public static void main(String[] args) {
        SpringApplication.run(HackathonMediboxDemoApplication.class, args);
    }

}
