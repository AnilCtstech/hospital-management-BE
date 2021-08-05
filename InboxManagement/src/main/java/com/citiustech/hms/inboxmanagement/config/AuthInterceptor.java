package com.citiustech.hms.inboxmanagement.config;

import java.util.Iterator;

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
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		if (!request.getMethod().equals("OPTIONS")) {
			restTemplate = new RestTemplate();
			String url = "http://localhost:8088/authenticate";
			HttpHeaders headers = new HttpHeaders();

			headers.add("Authorization", request.getHeader("Authorization"));
			headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
			return true;
		}
		return true;

	}

}
