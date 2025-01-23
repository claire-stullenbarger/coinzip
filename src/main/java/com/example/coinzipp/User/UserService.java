package com.example.coinzipp.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public boolean authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }

    public User createUser(User user) {
        // Add any business logic (e.g., password hashing)
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public User registerNewUser(RegistrationRequest registrationRequest) throws Exception {
        // Check if email already exists
        if (userRepository.findByEmail(registrationRequest.getEmail()) != null) {
            throw new Exception("Email already in use");
        }

        // Validate password strength
        validatePassword(registrationRequest.getPassword());

        User user = new User();
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setName(registrationRequest.getName());
        user.setAccountType(registrationRequest.getAccountType());

        return createUser(user);
    }

    private void validatePassword(String password) throws Exception {
        if (password.length() < 8) {
            throw new Exception("Password must be at least 8 characters");
        }
        // Add more password strength checks
    }

    // Add more password strength checks
}



