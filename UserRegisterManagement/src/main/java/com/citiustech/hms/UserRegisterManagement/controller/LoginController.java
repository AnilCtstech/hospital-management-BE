package com.citiustech.hms.UserRegisterManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citiustech.hms.UserRegisterManagement.model.Login;
import com.citiustech.hms.UserRegisterManagement.service.LoginService;
import com.citiustech.hms.UserRegisterManagement.utils.LoginStatus;

@RestController
@RequestMapping("/login")
public class LoginController {
	@Autowired
private LoginService loginService;

//login Patient
	@PostMapping("/")
	public LoginStatus userLogin(@RequestBody Login login) {
		return loginService.userLogin(login);
	}
}
