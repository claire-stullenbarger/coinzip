package com.example.coinzipp.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndPassword(String email, String password);

    User findByEmail(String email);
    void deleteByEmail(String email);

}
