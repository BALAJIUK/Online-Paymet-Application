package com.cg.security.filter;

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

import com.cg.entities.CustomerToken;
import com.cg.exception.SessionException;
import com.cg.repositories.CustomerTokenRepository;
import com.cg.security.utility.JWTUtility;
import com.cg.service.IuserServiceImplementation;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JWTUtility jwtUtility;

	@Autowired
	private IuserServiceImplementation userService;

	@Autowired
	CustomerTokenRepository customerTokenRepository;

	public void checkToken(HttpServletRequest httpServletRequest) {
		String authorization = httpServletRequest.getHeader("Authorization");

		if (null != authorization && authorization.startsWith("Bearer ")) {
			String token = authorization.substring(7);
			String mobileNo = jwtUtility.getMobileNoFromToken(token);
			CustomerToken customerToken = customerTokenRepository.getByMobileNo(mobileNo);
			if (customerToken == null) {
				userService.sessionExpired();
			}
		}
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws IOException, ServletException{
		String authorization = httpServletRequest.getHeader("Authorization");
		String token = null;
		String mobileNo = null;

		if (null != authorization && authorization.startsWith("Bearer ")) {
			token = authorization.substring(7);
			mobileNo = jwtUtility.getMobileNoFromToken(token);
			CustomerToken customerToken = customerTokenRepository.getByMobileNo(mobileNo);
			if (customerToken == null) {
				userService.sessionExpired();
			}
			token = customerToken.getToken();

		}

		if (null != mobileNo && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userService.loadUserByUsername(mobileNo);

			if (jwtUtility.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}

		}
			filterChain.doFilter(httpServletRequest, httpServletResponse);	
	}
}
