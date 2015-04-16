package com.proyecto.dao;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.proyecto.jdbc.UserRowMapper;
import com.proyecto.proyecto.User;


public class UserDaoImpl implements UserDao {

	@Autowired
	DataSource dataSource;
	
	@Override
	public void insertData(User user) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO apps (nombre,code, status,password) VALUES ( ?, ?, ?, ?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(
				sql,new Object[] {user.getNombre(),user.getCode(), user.getStatus(), user.getPassword() });
		
	}

	@Override
	public void updateData(User user) {
		// TODO Auto-generated method stub
		String sql = "UPDATE apps set code = ? where nombre = '"+user.getNombre()+"' and password = '"+user.getPassword()+"'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql, new Object[] { user.getCode()});
	}

	@SuppressWarnings("unchecked")
	public User getUser(String nombre, String password, String code) {
		// TODO Auto-generated method stub
		
		List<User> userList = new ArrayList<User>();
		List<User> userList2 = new ArrayList<User>();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	
			Random random = new SecureRandom();
		 	String token = md5(Integer.toString(random.nextInt()));
		 	
		 	String sql2 = "UPDATE apps set code = ? where nombre = '"+nombre+"' and password = '"+password+"'";
			JdbcTemplate jdbcTemplate2 = new JdbcTemplate(dataSource);
			jdbcTemplate.update(sql2, new Object[] { token });
			
			String sql3 = "select * from apps where nombre= '"+nombre+"' and password='"+password+"'";
			JdbcTemplate jdbcTemplate3 = new JdbcTemplate(dataSource);
			userList = jdbcTemplate3.query(sql3, new UserRowMapper());
			
			return userList.get(0);
		 	
	}

	@Override
	public List<User> getUserList(String name) {
		List userList = new ArrayList();
		String sql = "select * from apps where nombre = '"+name+"'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		userList = jdbcTemplate.query(sql, new UserRowMapper());
		return userList;
		
	}

	public static String md5(String input) {
        
        String md5 = null;
      
        if(null == input) return null;
         
        try {
             
        //Create MessageDigest object for MD5
        MessageDigest digest = MessageDigest.getInstance("MD5");
         
        //Update input string in message digest
        digest.update(input.getBytes(), 0, input.length());
 
        //Converts message digest value in base 16 (hex) 
        md5 = new BigInteger(1, digest.digest()).toString(16);
 
        } catch (NoSuchAlgorithmException e) {
 
            e.printStackTrace();
        }
        return md5;
    }
	
	

}
