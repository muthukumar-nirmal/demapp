package com.demapp.service;

import java.util.List;

import com.demapp.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
	public List<User> listUsers();
	void deleteUser(Long id);
	User getUserByID(Long id);
	public User getCurrentUser();
}
