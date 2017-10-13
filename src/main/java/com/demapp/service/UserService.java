package com.demapp.service;

import com.demapp.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
