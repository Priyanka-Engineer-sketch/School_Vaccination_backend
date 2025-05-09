package com.schoolvaccination.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.schoolvaccination.entity.CustomUserDetails;
import com.schoolvaccination.entity.Role;
import com.schoolvaccination.entity.User;
import com.schoolvaccination.repository.UserRepository;
import com.schoolvaccination.util.JwtUtil;

import jakarta.annotation.PostConstruct;

@Service
public class UserService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
	}

	public User registerUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		if (user.getRole() == null) {
			user.setRole(Role.PARENT);
		}
		return userRepository.save(user);
	}

	public String generateToken(User user) {
		return jwtUtil.generateToken(user.getUsername(), user.getRole().name());
	}

	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    User user = userRepository.findByEmail(email)
	        .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
	    return new CustomUserDetails(user);
	}


	@PostConstruct
	public void initAdmin() {
		if (userRepository.findByEmail("admin@school.com").isEmpty()) {
			User admin = new User();
			admin.setUsername("admin");
			admin.setEmail("admin@school.com");
			admin.setPassword(passwordEncoder.encode("admin123"));
			admin.setRole(Role.ADMIN);
			userRepository.save(admin);
			logger.info("Default admin user created in PostgreSQL");
		}
	}

}
