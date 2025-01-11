package com.homekitchen.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseCookie.ResponseCookieBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.homekitchen.api.*;
import com.homekitchen.api.entity.*;
import com.homekitchen.api.security.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping(produces="application/json")
public class UserController extends ControllerBase {
	@Autowired 
	AuthenticationManager authenticationManager;
	
	@GetMapping("/getUserById")
	public @ResponseBody List<User> getUserById(Integer id) {
		return userRepository.findUserById(id);
	}
	
	@GetMapping("/getUserByEmail")
	public @ResponseBody List<User> getUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}
	
	@GetMapping("/getUserByPhone")
	public @ResponseBody List<User> getUserByPhone(String phone) {
		return userRepository.findUserByPhone(phone);
	}
	
	// User
	@PostMapping(value="/addUser", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseMessage> createUser(User user) {
		if (user.getFirstName().isEmpty() || user.getPassWord().isEmpty()) {
			return ResponseEntity.ok(new ResponseMessage(ResponseMessage.STATUS.Error, "userName and passWord can't be empty"));
		}
		if (user.getEmail().isEmpty()) {
			return ResponseEntity.ok(new ResponseMessage(ResponseMessage.STATUS.Error, "Please provide an email"));
		}
		if (user.getPhone().isEmpty()) {
			return ResponseEntity.ok(new ResponseMessage(ResponseMessage.STATUS.Error, "Please provide a phone number"));
		}
		
		User added = userRepository.insertUser(user);
		return ResponseEntity.ok(new ResponseMessage(ResponseMessage.STATUS.Success, "User " + user.getEmail() + " registered successfully", added.getId()));
		
	}
	
	@PostMapping(value = "/login")
	@ResponseBody
	  public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
		Authentication auth ;
	    try {
	      auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
	    } catch (BadCredentialsException e) {
	      throw e;
	    }

	    AuthenticatedUser  auser = (AuthenticatedUser)auth.getPrincipal();
	    String token = JwtHelper.generateToken(request.email());
	    return ResponseEntity.ok().body(new LoginResponse(auser.getId(), auser.getFirstName(), auser.getLastName(), request.email(), token));
	    
	  }
}
