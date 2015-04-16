package com.proyecto.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.proyecto.dao.UserDao;
import com.proyecto.proyecto.User;

public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userdao;

	public void insertData(User user) {
		userdao.insertData(user);
	}

	@Override
	public void updateData(User user) {
		// TODO Auto-generated method stub
		userdao.updateData(user);
	}

	@Override
	public User getUser(String nombre, String password, String code) {
		// TODO Auto-generated method stub
		return userdao.getUser(nombre,password,code);
	}

	@Override
	public List<User> getUserList(String name) {
		// TODO Auto-generated method stub
		return userdao.getUserList(name);
	}

}
