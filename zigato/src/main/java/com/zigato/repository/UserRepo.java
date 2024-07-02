package com.zigato.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zigato.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
	public Optional<User> findByEmail(String username);
}
