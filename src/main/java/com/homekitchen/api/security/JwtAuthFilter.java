package com.homekitchen.api.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	//@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	public JwtAuthFilter(UserDetailsServiceImpl userDetailsService) {
	  this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
		      String authHeader = request.getHeader("Authorization");

		      String token = null;
		      String username = null;
		      if (authHeader != null && authHeader.startsWith("Bearer ")) {
		        token = authHeader.substring(7);
		        username = JwtHelper.extractUsername(token);
		      }

//		      If the accessToken is null. It will pass the request to next filter in the chain.
//		      Any login and signup requests will not have jwt token in their header, therefore they will be passed to next filter chain.
		      if (token == null) {
		        filterChain.doFilter(request, response);
		        return;
		      }

//		       If any accessToken is present, then it will validate the token and then authenticate the request in security context
		      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
		        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		        if (JwtHelper.validateToken(token, userDetails)) {
		          UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, null);
		          authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		        }
		      }

		      filterChain.doFilter(request, response);
		    } catch (Exception e) {
		    	throw new ServletException (e);
		      //String json = "{ \""+ HttpServletResponse.SC_FORBIDDEN+"\" : \"" + e.getMessage() + "\" } ";
		      //response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		      //response.getWriter().write(json);
		    } 
		
	}

}
