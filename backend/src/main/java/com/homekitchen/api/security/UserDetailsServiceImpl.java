package com.homekitchen.api.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.homekitchen.api.entity.User;
import com.homekitchen.api.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
			List<User> users = userRepository.findUserByEmail(email);
	    	
	    	User user= users.get(0);
	    	return buildAuthenticatedUser(user);
			
		} catch (Exception ex) {
			throw new UsernameNotFoundException(String.format("User does not exist, email: %s", email));
		}
	}
	
	private AuthenticatedUser buildAuthenticatedUser(User user) {
		//UserDetails userDetail = (AuthenticatedUser)org.springframework.security.core.userdetails.User.builder()
		//        .username(user.getEmail())
		//        .password(user.getPassWord())
		//        .build();
		
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		auths.add(new SimpleGrantedAuthority("ROLE_USER"));
		AuthenticatedUser aUser = new AuthenticatedUser(user.getEmail(), user.getPassWord(), true, true, true, true, auths);
		aUser.setEmail(user.getEmail());
		aUser.setFirstName(user.getFirstName());
	    aUser.setLastName(user.getLastName());
	    aUser.setPhone(user.getPhone());
	    aUser.setId(user.getId());
	    
	    return aUser;
	}

}
