package com.qraccess.daos.imp;


import com.qraccess.daos.interfaces.AdminDao;
import com.qraccess.entities.Access;
import com.qraccess.entities.Admin;
import com.qraccess.entities.User;

public class AdminDaoImpl extends DBManager<Admin> implements AdminDao {

	@Override
	public String createAccess(Access a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createUser(User u) {
		// TODO Auto-generated method stub
		new UserDaoImpl(u);
		return null;
	}
}
