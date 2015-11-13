package com.test.app.services;

import java.util.ArrayList;

import com.test.app.entities.User;

public interface UserService {
	
	User getUser(int id);
	boolean insertUser(User user);
	boolean updateUser(User user);
	boolean deleteUser(int id);
	ArrayList<User> listUser();
	int getMaxID(String tblName);
}
