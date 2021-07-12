package com.citiustech.hms.inboxmanagement.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;


@Service
public class AuthInterceptor implements HandlerInterceptor {


	private RestTemplate restTemplate;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,
            Object handler)
     {
		restTemplate=new RestTemplate();
		String url="http://localhost:8088/authenticate";
		HttpHeaders headers=new HttpHeaders();
		headers.add("Authorization", request.getHeader("Authorization"));
		HttpEntity<String> entity=new HttpEntity<String>(headers);
		ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		return true;
		
	}
	
}
