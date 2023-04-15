package com.qraccess.daos.imp;

import java.util.List;

import com.qraccess.daos.interfaces.UserDao;
import com.qraccess.entities.Access;
import com.qraccess.entities.Customer;

public class UserDaoImpl extends CrudDaoImpl<Customer> implements UserDao {

	public UserDaoImpl() {}
	
	public UserDaoImpl(Customer user) {
		this.insert(user);
	}
	
	public static void createUser(Customer user) {
		new UserDaoImpl(user);
	}

	@Override
	public String showQr() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Class<Customer> getEntityClass(){
		return Customer.class;
	}
	
	public Customer getById(int id) {
		return (Customer)super.getById(id);
	}
}
