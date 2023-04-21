package com.qraccess.daos.imp;

import com.qraccess.daos.interfaces.CustomerDao;
import com.qraccess.entities.Customer;

public class CustomerDaoImpl extends CrudDaoImpl<Customer> implements CustomerDao {

	public CustomerDaoImpl() {}
	
	public CustomerDaoImpl(Customer user) {
		this.insert(user);
	}
	
	public static void createUser(Customer user) {
		new CustomerDaoImpl(user);
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

	public Customer updateEntity(Customer entity, Customer updates) {
		entity.setUser(updates.getUser());
		return entity;
	}
	
	
}
