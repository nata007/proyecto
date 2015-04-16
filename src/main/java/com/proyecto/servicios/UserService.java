package com.proyecto.servicios;



import java.util.List;

import com.proyecto.proyecto.User;

public interface UserService {
	public List<User> getUserList(String name);
	public void insertData(User user);
	public void updateData(User user);
	public User getUser(String nombre, String password, String code);
	

}
