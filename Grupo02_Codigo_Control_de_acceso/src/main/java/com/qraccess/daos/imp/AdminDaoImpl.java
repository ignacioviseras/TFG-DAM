package com.qraccess.daos.imp;


import com.qraccess.daos.interfaces.AdminDao;
import com.qraccess.entities.Access;
import com.qraccess.entities.Admin;
import com.qraccess.entities.User;

public class AdminDaoImpl extends CrudDaoImpl<Admin> implements AdminDao {

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

	public AdminDaoImpl() {}
	
	public  AdminDaoImpl(Admin admin) {
		this.insert(admin);
	}
	
	public static void createAdmin (Admin admin) {
		new AdminDaoImpl(admin);
	}
	
	
	
}
