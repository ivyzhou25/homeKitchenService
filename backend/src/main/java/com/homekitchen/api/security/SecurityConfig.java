package com.homekitchen.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	//@Autowired
	private UserDetailsServiceImpl userDetailsService;

	//@Autowired
	private JwtAuthFilter jwtAuthFilter;
	
	public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtAuthFilter jwtAuthFilter) {
	this.userDetailsService = userDetailsService;
	this.jwtAuthFilter = jwtAuthFilter;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
	}

// 	  @Bean
// 	  public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
// 		  return http.cors(AbstractHttpConfigurer::disable)
// 	    	 .csrf(AbstractHttpConfigurer::disable)
// 	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
// 	        .authorizeHttpRequests(auth -> auth
// //	              our public endpoints
// 	            //.requestMatchers("/order/**").authenticated()
// //	              our private endpoints
// 	            //.anyRequest().permitAll()
// 				.requestMatchers("/order/add").permitAll()     // <-- Make this public
// 				// .requestMatchers("/order/**").authenticated()  // All other /order routes require auth
// 				.requestMatchers("/getAllBiz").permitAll()
// 				.anyRequest().permitAll()
// 			)
// 	        .authenticationManager(authenticationManager)
// 	        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
// 	        .build();
// 	  }
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		System.out.println("Security filter chain initialized — permit all requests");
		return http
			.cors(AbstractHttpConfigurer::disable)
			.csrf(AbstractHttpConfigurer::disable)
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
			.build();
	}

	  
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
	AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
	authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	return authenticationManagerBuilder.build();
	}	  
}
