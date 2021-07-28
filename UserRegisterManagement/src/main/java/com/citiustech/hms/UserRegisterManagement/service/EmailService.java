package com.citiustech.hms.UserRegisterManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private JavaMailSender javaMailSender;

	@Autowired
	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public boolean sentEmail(String password) {
		boolean mailSent = false;
		if (password != null) {
			try {
				sendingMail(password);
				mailSent = true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return mailSent;
	}

	private void sendingMail(String password) {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setTo("Ramakant.Samantray@citiustech.com");
		message.setSubject("Login Password");
		message.setText("your password for login :" + password);
		javaMailSender.send(message);
	}

	public void sendEmailtoUser(String email, String password) {
		if (password != null) {
			try {
				sendingMailForgetPassword(password);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private void sendingMailForgetPassword(String password) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("Ramakant.Samantray@citiustech.com");
		message.setSubject("Login Password");
		message.setText("your login password  :" + password);
		javaMailSender.send(message);

	}

	public void sendCustomEmail(String to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		javaMailSender.send(message);
	}

}
