package com.vino.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vino.test.models.Users;

public interface UserRepository extends JpaRepository<Users, String> {
	Users findByEmail(String email);
    Users findByUsername(String username);
}
