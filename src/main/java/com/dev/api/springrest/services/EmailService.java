package com.dev.api.springrest.services;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
@Component
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;

	private final String emailRemetente =  "anacsocanto@gmail.com";//"matheus.r.freitas@aluno.senai.br";//"renan.ribeiro15@hotmail.com";

	public JavaMailSender javaMailSender() {

		JavaMailSenderImpl enviarEmail = new JavaMailSenderImpl();
		Properties prop = new Properties();

		enviarEmail.setHost("smtp.gmail.com");
		enviarEmail.setPort(465);
		enviarEmail.setUsername("group.two.serratec@gmail.com");
		enviarEmail.setPassword("Admin123456.");
		enviarEmail.setProtocol("smtp");
		enviarEmail.setDefaultEncoding("UTF-8");
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.ssl.enable", true);
		enviarEmail.setJavaMailProperties(prop);

		return enviarEmail;
	}

	public void sendMessage(String to, String subject, String text) {

		SimpleMailMessage messege = new SimpleMailMessage();
		messege.setFrom(emailRemetente);
		messege.setTo(to);
		messege.setSubject(subject);
		messege.setText(text);
		emailSender.send(messege);
	}

	public void emailTeste() throws MessagingException/* , EmailException */ {
		this.emailSender = javaMailSender();
		MimeMessage messege = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(messege);

		try {
			helper.setFrom("anacsocanto@gmail.com");//("renan.ribeiro15@hotmail.com");
			helper.setTo(emailRemetente);
			helper.setSubject("Assunto do Email");

			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("<html> \r\n" 
					+ "<body>\r\n" 
					+ "<br/>" 
					+ "########################<br/>"
					+ "O cartão foi criado com sucesso! <br/>" 
					+ "########################<br/>" 
					+ "Nome:  <br/>" 
					+ "Numero do cartão : <br/>" 
					+ "########################<br/>"
					+ "Att,<br/>" 
					+ "Equipe Agiota's Bank, seu dinheiro é o nosso dinheiro<br/>" + "<br/>"
					+ "</div>\r\n" 
					+ "</body>\r\n" 
					+ "</html> \r\n");
			helper.setText(sBuilder.toString(), true);
			emailSender.send(messege);
		} catch (Exception e) {
		}
	}

}
