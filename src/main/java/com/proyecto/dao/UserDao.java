package com.proyecto.dao;


import java.util.List;

import com.proyecto.proyecto.User;

public interface UserDao {
	
	public List<User> getUserList(String name);
	public void insertData(User user);
	public void updateData(User user);
	public User getUser(String nombre, String password, String code);

}
