package com.fsoft.logincustom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fsoft.dto.AdminDTO;
import com.fsoft.service.impl.AdminServiceImpl;

@Component
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	public static final String URL_LOGIN_FAILURE = "/login?error";

	@Autowired
	private AdminServiceImpl adminServiceImpl;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String username = request.getParameter("username");
		AdminDTO admin = adminServiceImpl.findByUsername(username);
		
		if (admin == null) {
			exception = new UsernameNotFoundException("Username is not exist");
		}
		
		if (exception.getMessage().contains("Bad credentials")) {
			exception = new BadCredentialsException("Your username and password is invalid.");
		}
		
		if (admin != null) {
			if (admin.isAccountNonLocked()) {
				if (admin.getFailedAttempt() < AdminServiceImpl.MAX_FAILED_ATTEMPTS - 1) {
					adminServiceImpl.increaseFailedAttempts(admin);
					
				} else {
					adminServiceImpl.lock(admin);
					exception = new LockedException("Your account has been locked due to 3 failed attempts."
							+ " It will be unlocked after 24 hours.");
					
				}
			} else if (!admin.isAccountNonLocked()) {
				if (adminServiceImpl.unlockWhenTimeExpired(admin) == false) {
					exception = new LockedException("Your account has been locked. Please try to login again.");
				}else {
					response.sendRedirect("/dashboard");

				}
			}
		}

		super.setDefaultFailureUrl(URL_LOGIN_FAILURE);
		super.onAuthenticationFailure(request, response, exception);
	}
}
