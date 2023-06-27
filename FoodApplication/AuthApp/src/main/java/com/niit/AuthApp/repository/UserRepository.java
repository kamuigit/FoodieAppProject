package com.niit.AuthApp.repository;

import com.niit.AuthApp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    public User findByEmailAndPassword(String email, String Password);
    public User findByEmail(String email);
}
