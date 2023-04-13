package com.qraccess.daos.imp;

import com.qraccess.daos.interfaces.UserDao;
import com.qraccess.entities.User;

public class UserDaoImpl extends DBManager<User> implements UserDao {

	public UserDaoImpl() {
		
	}
	
	public UserDaoImpl(User user) {
		this.create(user);
	}
	
	public static void createUser(User user) {
		new UserDaoImpl(user);
	}	
	
	@Override
	public User update(User obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
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
