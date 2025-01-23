package com.example.coinzipp.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    void deleteByEmail(String email);
    User findByEmailAndPassword(String email, String password);
    User findbyId(Long id);
}
