package com.shoppinglist.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Value("${spring.mail.username}")
	private String MESSAGE_FROM;
	
	private static final String MAIL_SUBJECT = "Succesfull registration";
	
	private JavaMailSender javaMailSender;

	@Autowired
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}


	public void sendMessage(String activation,String email,String username) {
		SimpleMailMessage message = null;
		
		try {
			message = new SimpleMailMessage();
			message.setFrom(MESSAGE_FROM);
			message.setTo(email);
			message.setSubject(MAIL_SUBJECT);
			message.setText("Dear " +" "+ username+ "! \n \n Thank you for the registration! \n\n Welcome to the shoppinglist application"
					+"\n \n Please activate your registration" +"  \n \n \n   http://localhost:8080" + "/activation/"   + activation  );
			javaMailSender.send(message);
			
			
		} catch (Exception e) {
			System.out.println("Hiba e-mail küldéskor az alábbi címre: " + email + "  " + e);
		}
		

	}
}
