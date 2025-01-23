package com.example.coinzipp.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        if (userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
        @PostMapping("/register")
        public ResponseEntity<?> register(@RequestBody RegistrationRequest registrationRequest) {
            try {
                User newUser = userService.registerNewUser(registrationRequest);
                return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
            }
        }


    }
