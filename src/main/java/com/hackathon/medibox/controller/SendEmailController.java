package com.hackathon.medibox.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.medibox.SwaggerConfig2;
import com.hackathon.medibox.model.EmailData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.AccountSendingPausedException;
import software.amazon.awssdk.services.ses.model.Body;
import software.amazon.awssdk.services.ses.model.Content;
import software.amazon.awssdk.services.ses.model.Destination;
import software.amazon.awssdk.services.ses.model.MailFromDomainNotVerifiedException;
import software.amazon.awssdk.services.ses.model.Message;
import software.amazon.awssdk.services.ses.model.MessageRejectedException;
import software.amazon.awssdk.services.ses.model.SendEmailRequest;
import software.amazon.awssdk.services.ses.model.SesException;

@Api(tags = { SwaggerConfig2.EMAIL_TAG })
@RestController
@RequestMapping("/emails")
public class SendEmailController {

    private final Logger LOGGER = LoggerFactory.getLogger(SendEmailController.class);

    private static final String ERROR_MSG_PREFIX = "The message could not be sent due to: ";

    private final SesClient amazonSimpleEmailService;

    @Autowired
    public SendEmailController(SesClient amazonSimpleEmailService) {
        this.amazonSimpleEmailService = amazonSimpleEmailService;
    }

    @ApiOperation(value = "Send notification")
    @PostMapping("/send")
    @ResponseBody
    public void sendEmailNotification(@RequestBody EmailData emailData) {
        LOGGER.info("Request reached to SendEmailController sendEmailNotification method");
        String source = emailData.getFromEmailAddress();
        String replyToAddress = source;
        String toAddress = emailData.getToEmailAddress();
        String subject = emailData.getSubject();
        String body = emailData.getBody();
        sendEmail(source, replyToAddress, toAddress, subject, body);
    }

    /**
     * 
     * @param source
     *            - from email address
     * @param replyToAddress
     *            - setting reply to email address
     * @param toAddress
     *            - to email address
     * @param subject
     *            - subject of email
     * @param htmlBody
     *            - content of the email, must be wrapped in html
     * @throws SesException
     */
    public void sendEmail(String source, String replyToAddress, String toAddress, String subject, String htmlBody) {
        LOGGER.debug("Sending email with source = {}, toAddress = {} and subject = {}", source, toAddress, subject);
        try {
            Destination destination = Destination.builder().toAddresses(toAddress).build();
            Content content = Content.builder().data(htmlBody).build();
            Content sub = Content.builder().data(subject).build();
            Body body = Body.builder().html(content).build();
            Message msg = Message.builder().subject(sub).body(body).build();

            SendEmailRequest emailRequest = SendEmailRequest.builder().destination(destination).message(msg)
                    .source(source).replyToAddresses(replyToAddress).build();
            amazonSimpleEmailService.sendEmail(emailRequest);
            LOGGER.debug("Email sent!");
        } catch (MessageRejectedException | MailFromDomainNotVerifiedException | AccountSendingPausedException e) {
            throw SesException.builder().message(String.format(ERROR_MSG_PREFIX, e.getMessage())).build();
        } catch (Exception e) {
            throw SesException.builder().message(String.format(ERROR_MSG_PREFIX, e.getMessage())).build();
        }
    }

}
