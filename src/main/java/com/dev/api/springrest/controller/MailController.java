package com.dev.api.springrest.controller;

import com.dev.api.springrest.service.EmailService;
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
    	String product = "Produto e-mail";
    	Integer amounts = 5;
    	Double value = 2.0;
    	mailSend.emailSale(product, amounts, value);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @PostMapping("/emailProductInventory")
    public ResponseEntity<Void> emailProductInventory() throws MessagingException {
    	String product = "Produto e-mail";
    	Integer amounts = 1;
    	mailSend.emailProductInventory(product, amounts);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
