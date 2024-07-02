package com.zigato.service;

import com.zigato.model.User;

public interface UserService {

	public User findUserByJwtToken(String jwt) throws RuntimeException;

	public User findUserByEmail(String email) throws RuntimeException;

}
