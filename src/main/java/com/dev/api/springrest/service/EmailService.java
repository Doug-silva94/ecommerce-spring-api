package com.dev.api.springrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String userName;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.password}")
    private String password;

    private final String emailSender = "renan.ribeiro15@hotmail.com";

    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl sendMail = new JavaMailSenderImpl();
        Properties props = new Properties();
        sendMail.setHost(host);
        sendMail.setPort(465);
        sendMail.setUsername(userName);
        sendMail.setPassword(password);
        sendMail.setProtocol("smtp");
        sendMail.setDefaultEncoding("UTF-8");
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.ssl.enable", true);
        sendMail.setJavaMailProperties(props);

        return sendMail;
    }

    public void sendMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailSender);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void mailTest() throws MessagingException {
        this.mailSender = javaMailSender();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        try {
            helper.setFrom("serratecRenan@gmail.com");
            helper.setTo(emailSender);
            helper.setSubject("DIGITE AQUI SEU TITULO");

            StringBuilder sb = new StringBuilder();
            sb.append("DIGITA AQUI SUA MENSAGEM");
            helper.setText(sb.toString(), true);
            mailSender.send(message);
        } catch (Exception e) {
            System.out.println("deu merda" + e.getMessage());
        }
    }

}
