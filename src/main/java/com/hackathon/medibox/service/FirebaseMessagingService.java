package com.hackathon.medibox.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.hackathon.medibox.model.Note;

@Service
public class FirebaseMessagingService {

    private final Logger LOGGER = LoggerFactory.getLogger(FirebaseMessagingService.class);

    private final FirebaseMessaging firebaseMessaging;

    public FirebaseMessagingService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }

    public String sendNotification(Note note, String topic) throws FirebaseMessagingException {
        LOGGER.info("Reached FirebaseMessagingService class sendNotification method");
        Notification notification = Notification
                .builder()
                .setTitle(note.getSubject())
                .setBody(note.getContent())
                .setImage(note.getImage())
                .build();
        Message message = Message
                .builder()
                .setTopic(topic)
                .setNotification(notification)
                .putAllData(note.getData())
                .build();
        LOGGER.info("Finished FirebaseMessagingService class sendNotification method");
        return firebaseMessaging.send(message);
    }

}