package com.proyecto.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import com.proyecto.proyecto.User;

public class UserExtractor implements ResultSetExtractor<User> {

	public User extractData(ResultSet resultSet) throws SQLException,
			DataAccessException {
		
		User user = new User();
		
		user.setId(resultSet.getInt(1));
		user.setNombre(resultSet.getString(2));
		user.setCode(resultSet.getString(3));
		user.setStatus(resultSet.getInt(4));
		
		
		return user;
	}

}
