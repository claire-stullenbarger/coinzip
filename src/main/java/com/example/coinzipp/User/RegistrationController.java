package com.example.coinzipp.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest request) {
        // Validate user input
        if (request.getName() == null || request.getEmail() == null || request.getPassword() == null || request.getAccountType() == null) {
            return ResponseEntity.badRequest().body("All fields are required");
        }

        // Check if user already exists
        if (userRepository.findByEmail(request.getEmail()) != null) {
            return ResponseEntity.badRequest().body("User already exists");
        }

        // Create a new User entity
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setAccountType(request.getAccountType());

        // Save the new user to the database
        userRepository.save(user);

        return ResponseEntity.ok("Registration successful");
    }
}