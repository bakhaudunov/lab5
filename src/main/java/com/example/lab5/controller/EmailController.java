package com.example.lab5.controller;

import com.example.lab5.service.SmtpMailSender;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    SmtpMailSender smtpMailSender;

    @GetMapping(path = "/api/mail/send")
    public void sendMail() throws MessagingException {
        smtpMailSender.sendMail("rashidrakhmidinov@gmail.com", "testmail", "Привет! Это я твой единственный друг!");
    }
}