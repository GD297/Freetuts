package com.fsoft.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fsoft.logincustom.CustomLoginFailureHandler;
import com.fsoft.logincustom.CustomLoginSuccessHandler;
import com.fsoft.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Qualifier("userDetailsServiceImpl")
	@Autowired
	private UserDetailsService userDetailService;

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
			authProvider.setUserDetailsService(userDetailsService());
			authProvider.setPasswordEncoder(bCryptPasswordEncoder());
			
			return authProvider;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
//		http.authorizeRequests().antMatchers("/resources/**", "/registration").permitAll().anyRequest().authenticated()
//				.and().formLogin().loginPage("/login").defaultSuccessUrl("/welcome").permitAll().and().logout().permitAll();
		http.authorizeRequests()
				.antMatchers("/login**").permitAll()
				.antMatchers("/dashboard").hasRole("ADMIN")
				.antMatchers("/login/**").hasRole("ADMIN")
				.antMatchers("/navigation").hasRole("ADMIN")
				.antMatchers("/profile").hasRole("ADMIN")
				.antMatchers("/category").hasRole("ADMIN")
				.antMatchers("/add-category").hasRole("ADMIN")
				.antMatchers("/edit-category/**").hasRole("ADMIN")
				.antMatchers("/subCategory").hasRole("ADMIN")
				.antMatchers("/add-subCategory").hasRole("ADMIN")
				.antMatchers("/edit-subCategory/**").hasRole("ADMIN")
				.antMatchers("/totalblog/**").hasRole("ADMIN")
				.antMatchers("/", "/**", "/public/**","/upload/**").permitAll().anyRequest().authenticated()
				
				.and().formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/login")
//			.defaultSuccessUrl("/dashboard",true)
//			.failureUrl("/login?err")
				.failureHandler(loginFailureHandler)
		        .successHandler(loginSuccessHandler)    
				.permitAll()
			.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login?logout").permitAll()
			.and().exceptionHandling();
		
		  // Cấu hình Remember Me.
        http.authorizeRequests().and() 
                .rememberMe().rememberMeParameter("remember-me").tokenRepository(this.persistentTokenRepository()) //
                .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
	}
	
    @Autowired
    private CustomLoginFailureHandler loginFailureHandler;
     
    @Autowired
    private CustomLoginSuccessHandler loginSuccessHandler;
    
//    @Bean
//    public AuthenticationFailureHandler authenticationFailureHandler() {
//        return new CustomLoginFailureHandler();
//    }

	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
		return authenticationManager();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	
	// Token stored in Memory (Of Web Server).
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
	    InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
	    return memory;
	}
	
	
	
//	 public void configureGlobal(ContentNegotiationConfigurer configurer) {
//	        configurer
//	           .mediaType(MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON)
//	           .defaultContentType(MediaType.APPLICATION_JSON);
//	    }
}