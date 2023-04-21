package com.qraccess.daos.imp;

import com.qraccess.daos.interfaces.AccessDao;
import com.qraccess.entities.Access;
import com.qraccess.entities.Admin;
import com.qraccess.entities.Customer;

public class AccessDaoImpl extends CrudDaoImpl<Access> implements AccessDao {

	public AccessDaoImpl(){}

	public AccessDaoImpl(Access a){
		this.insert(a);
	}

	public static void createAccess(Access a) {
		new AccessDaoImpl(a);
	}

	@Override
	public void validate(int id, String hash) {
		// TODO Auto-generated method stub

	}

	public Class<Access> getEntityClass(){
		return Access.class;
	}

	//creo q se hace asi
	public Access getById(int id) {
		return (Access)super.getById(id);
	}
	
	//creo q se hace asi
	public Customer getCustomerByAccessesId(int id) {
		CustomerDaoImpl cs = new CustomerDaoImpl();
		return (Customer)cs.getById(id);
		
	}
	
	
	public Access updateEntity(Access entity, Access updates) {
		entity.setAvailables(updates.getAvailables());
		entity.setExpires(updates.getExpires());
		entity.setUuid(updates.getUuid());
		return entity;
	}

}
