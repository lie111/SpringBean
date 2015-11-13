package com.test.app.services;

import java.util.ArrayList;

import com.test.app.entities.User;

public interface UserService {
	
	User getUser(int id);
	boolean insertUser(User stu);
	boolean updateUser(User stu);
	boolean deleteUser(int id);
	ArrayList<User> listUser();
	int getMaxID(String tblName);
}
