package com.hackathon.medibox;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;

import software.amazon.awssdk.services.ses.SesClient;

@SpringBootApplication
public class HackathonMediboxDemoApplication {

    Logger logger = LoggerFactory.getLogger(HackathonMediboxDemoApplication.class);

    @Bean
    FirebaseMessaging firebaseMessaging() throws IOException {
        try {
            GoogleCredentials googleCredentials = GoogleCredentials
                    .fromStream(new ClassPathResource("firebase-service-account.json").getInputStream());
            FirebaseOptions firebaseOptions = FirebaseOptions
                    .builder()
                    .setCredentials(googleCredentials)
                    .build();
            FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "my-app");
            logger.info("Firebase application has been initialized");
            return FirebaseMessaging.getInstance(app);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new IllegalArgumentException(
                    "Not able initialized firebase application, failed with error = " + e.getMessage());
        }
    }

    @Bean
    SesClient createSimpleEmailService() {
        return SesClient.builder().build();
    }

    public static void main(String[] args) {
        SpringApplication.run(HackathonMediboxDemoApplication.class, args);
    }

}
