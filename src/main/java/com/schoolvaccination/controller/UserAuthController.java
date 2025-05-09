package com.schoolvaccination.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schoolvaccination.dto.AuthRequest;
import com.schoolvaccination.dto.AuthResponse;
import com.schoolvaccination.entity.CustomUserDetails;
import com.schoolvaccination.entity.User;
import com.schoolvaccination.service.UserService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/v1/auth")
public class UserAuthController {

    private static final Logger logger = LoggerFactory.getLogger(UserAuthController.class);

    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public UserAuthController(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        logger.info("Registering user with email: {}", user.getEmail());
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        try {
            logger.info("Attempting authentication for email: {}", request.getEmail());

            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            User user = userDetails.getUser();
            String token = userService.generateToken(user);

            return ResponseEntity.ok(new AuthResponse(token, user.getEmail(), user.getRole().name()));

        } catch (AuthenticationException ex) {
            logger.error("Authentication failed for email: {}", request.getEmail(), ex);
            throw ex;
        }
    }
}
