package com.dev.api.springrest.controllers;

import com.dev.api.springrest.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/send")
public class MailController {

    @Autowired
    EmailService mailSend;

    @PostMapping
    public ResponseEntity<Void> sendMail() throws MessagingException {
       mailSend.emailTeste();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}

