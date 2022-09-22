package com.hackathon.medibox.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.hackathon.medibox.SwaggerConfig2;
import com.hackathon.medibox.model.Note;
import com.hackathon.medibox.service.FirebaseMessagingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { SwaggerConfig2.NOTIFICATION_TAG })
@RestController
@RequestMapping("/notifications")
public class TestPushNotification {
    private final Logger LOGGER = LoggerFactory.getLogger(TestPushNotification.class);

    private final FirebaseMessagingService firebaseService;

    @Autowired
    public TestPushNotification(FirebaseMessagingService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @ApiOperation(value = "Send notification")
    @PostMapping("/send")
    @ResponseBody
    public String sendNotification(@RequestBody Note note,
            @RequestParam String topic) throws FirebaseMessagingException {
        LOGGER.info("Request reached to TestPushNotification sendNotification method");
        return firebaseService.sendNotification(note, topic);
    }

}
