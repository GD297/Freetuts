package com.fsoft.logincustom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fsoft.entity.AdminEntity;
import com.fsoft.service.impl.AdminServiceImpl;
import com.fsoft.service.impl.UserDetailsService;

@Component
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	@Autowired
	private AdminServiceImpl adminServiceImpl;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		UserDetailsService userDetailsService = (UserDetailsService) authentication.getPrincipal();
		AdminEntity admin = userDetailsService.getAdmin();
		if(admin.getFailedAttempt() > 0) {
			adminServiceImpl.resetFailedAttempts(admin.getUsername());
		}
		response.sendRedirect("/dashboard");
		super.onAuthenticationSuccess(request, response, authentication);
	}
}

//public class CustomAuthenticationSuccessHandler implements
//AuthenticationSuccessHandler {
//
//private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
//
//@Override
//public void onAuthenticationSuccess(HttpServletRequest request,
//	HttpServletResponse response, Authentication authentication) throws IOException,
//	ServletException {
//HttpSession session = request.getSession();
//
///*Set some session variables*/
//User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();  
//session.setAttribute("uname", authUser.getUsername());  
//session.setAttribute("authorities", authentication.getAuthorities()); 
//
///*Set target URL to redirect*/
//String targetUrl = determineTargetUrl(authentication); 
//redirectStrategy.sendRedirect(request, response, targetUrl);
//}
//
//protected String determineTargetUrl(Authentication authentication) {
//Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
//if (authorities.contains("ROLE_ADMIN")) {
//	return "/admin.htm";
//} else if (authorities.contains("ROLE_USER")) {
//	return "/user.htm";
//} else {
//    throw new IllegalStateException();
//}
//}
//
//public RedirectStrategy getRedirectStrategy() {
//return redirectStrategy;
//}
//
//public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
//this.redirectStrategy = redirectStrategy;
//}
//}
