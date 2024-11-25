package com.example.coinzipp.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    User findByEmail(String email);
    void deleteByEmail(String email);
}
