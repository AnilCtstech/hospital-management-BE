package com.citiustech.hms.UserRegisterManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.hms.UserRegisterManagement.dto.EmailDTO;
import com.citiustech.hms.UserRegisterManagement.service.EmailService;

@RestController
@RequestMapping("/notification")
@CrossOrigin(origins = "http://localhost:4200")
public class EmailController {

	@Autowired
	EmailService emailService;

	@PostMapping("/email")
	public ResponseEntity<String> createEmail(@RequestBody EmailDTO emailDTO) {
		emailService.sendCustomEmail(emailDTO.getTo(), emailDTO.getSubject(), emailDTO.getBody());
		return ResponseEntity.ok("Email sent successfully");
	}
}
