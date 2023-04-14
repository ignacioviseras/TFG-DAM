package com.qraccess.daos.imp;

import java.util.List;

import com.qraccess.daos.interfaces.UserDao;
import com.qraccess.entities.User;

public class UserDaoImpl extends CrudDaoImpl<User> implements UserDao {

	public UserDaoImpl() {}
	
	public UserDaoImpl(User user) {
		this.insert(user);
	}
	
	public static void createUser(User user) {
		new UserDaoImpl(user);
	}

	@Override
	public String showQr() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public User getById(int id) {
		return (User)super.getById(id);
	}
}
