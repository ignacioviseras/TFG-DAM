package com.qraccess.daos.imp;


import com.qraccess.daos.interfaces.AdminDao;
import com.qraccess.entities.Access;
import com.qraccess.entities.Admin;
import com.qraccess.entities.Customer;

public class AdminDaoImpl extends CrudDaoImpl<Admin> implements AdminDao {

	@Override
	public String createAccess(Access a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createUser(Customer u) {
		// TODO Auto-generated method stub
		new CustomerDaoImpl(u);
		return null;
	}
	
	public Class<Admin> getEntityClass(){
		return Admin.class;
	}

	public AdminDaoImpl() {}
	
	public  AdminDaoImpl(Admin admin) {
		this.insert(admin);
	}
	
	public static void createAdmin (Admin admin) {
		new AdminDaoImpl(admin);
	}
	
	public Admin updateEntity(Admin entity, Admin updates) {
		entity.setUser(updates.getUser());
		return entity;
	}
	
}
