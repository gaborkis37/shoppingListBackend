package com.shoppinglist.backend.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.UserDeniedAuthorizationException;
import org.springframework.stereotype.Service;

import com.shoppinglist.backend.model.MyUserPrincipal;
import com.shoppinglist.backend.model.Role;
import com.shoppinglist.backend.model.User;
import com.shoppinglist.backend.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;
	private EmailService emailService;

	@Autowired
	public MyUserDetailsService(UserRepository userRepository, EmailService emailService) {
		super();
		this.userRepository = userRepository;
		this.emailService = emailService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new MyUserPrincipal(user);
	}

	public String registerUser(User userToRegister) {
		User userCheck = userRepository.findByUsername(userToRegister.getUsername());
		if (userCheck != null) {
			throw new UserDeniedAuthorizationException("Username already taken");
		}

		userToRegister.setRole(Role.USER);
		userToRegister.setEnabled(false);
		userToRegister.setActivation(generateKey());
		
		emailService.sendMessage(userToRegister.getActivation(), userToRegister.getEmail(), userToRegister.getUsername());
		userRepository.save(userToRegister);

		return "ok";
	}

	public String generateKey() {
		Random random = new Random();
		char[] word = new char[16];
		for (int j = 0; j < word.length; j++) {
			word[j] = (char) ('a' + random.nextInt(26));
		}
		return new String(word);
	}

	public void userActivation(String code) {
		User user = userRepository.findByActivation(code);
		if (user == null) {
			return;
		}

		user.setEnabled(true);
		user.setActivation("");
		userRepository.save(user);
	}

}
