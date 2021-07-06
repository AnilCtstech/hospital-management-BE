package com.citiustech.hms.UserRegisterManagement.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.citiustech.hms.UserRegisterManagement.service.CustomUserCredService;
import com.citiustech.hms.UserRegisterManagement.utils.JwtUtil;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CustomUserCredService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorization=request.getHeader("Authorization");
		String token=null;
		String email=null;
		if(authorization!=null && authorization.startsWith("Bearer ") ) {
			token=authorization.substring(7);
			//exception for invalid token
			email=jwtUtil.extractUsername(token);
		}
		
		if(email !=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails=userDetailsService.loadUserByUsername(email);
			
			if(jwtUtil.validateToken(token, userDetails)) {
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new 
						UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			
			}
		}
		filterChain.doFilter(request, response);
	}

	
}