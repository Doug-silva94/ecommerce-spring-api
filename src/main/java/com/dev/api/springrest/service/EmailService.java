package com.dev.api.springrest.service;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

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

    private final String emailSender [] = {"matheus.r.freitas@aluno.senai.br", 
    		"thaisquesada@live.com", "anacsocanto@gmail.com", "leo.chermaut@gmail.com", 
    		"douglas1.joker@gmail.com", "rodrigodepaula1989@gmail.com"};
    
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

//    public void sendMessage(String to, String subject, String text) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(emailSender);
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        mailSender.send(message);
//    }

    public void emailSale(String product, Integer amounts, Double value) throws MessagingException {
        this.mailSender = javaMailSender();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        try {
            helper.setFrom("matheus.r.freitas@aluno.senai.br");
            helper.setTo(emailSender);
            helper.setSubject("Mucha Lucha Store");

            StringBuilder sb = new StringBuilder();
            sb.append("<html> \r\n" 
					+ "<body>\r\n" 
					+ "<br/>" 
					+ "*****************************<br/>"
					+ "COMPRA REALIZADA COM SUCESSO! <br/>" 
					+ "*****************************<br/>"
					+ "Produto: " + product  + "<br/>" 
					+ "Quantidade: "  + amounts + "<br/>" 
					+ "Valor: R$" + (amounts * value) +  "<br/>" 
					+ "______________________________<br/>"
					+ "Att,<br/>" 
					+ "Mucha Lucha store<br/>" 
					+ "<br/>"
					+ "</div>\r\n" 
					+ "</body>\r\n" 
					+ "</html> \r\n");
            helper.setText(sb.toString(), true);
            mailSender.send(message);
        } catch (Exception e) {
            System.out.println("Houve um erro! Entre em contato com o suporte! " +e.getMessage());
        }
    }

    public void emailProductInventory(String product, Integer amounts) throws MessagingException {
        this.mailSender = javaMailSender();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        try {
            helper.setFrom("matheus.r.freitas@aluno.senai.br");
            helper.setTo(emailSender);
            helper.setSubject("URGENTE !!!");

            StringBuilder sb = new StringBuilder();
            sb.append("<html> \r\n" 
					+ "<body>\r\n" 
					+ "<br/>" 
					+ "====================================<br/>"
					+ "Produto com estoque baixo !!! <br/>" 
					+ "====================================<br/>" 
					+ "Produto: " + product  + "<br/>" 
					+ "Quantidade: "  + amounts + "<br/>" 
					+ "====================================<br/>"
					+ "Att,<br/>" 
					+ "Gerencia Mucha Lucha Store<br/>" 
					+ "<br/>"
					+ "</div>\r\n" 
					+ "</body>\r\n" 
					+ "</html> \r\n");
            helper.setText(sb.toString(), true);
            mailSender.send(message);
        } catch (Exception e) {
            System.out.println("Houve um erro! Entre em contato com o suporte! " +e.getMessage());
        }
    }
    
}
