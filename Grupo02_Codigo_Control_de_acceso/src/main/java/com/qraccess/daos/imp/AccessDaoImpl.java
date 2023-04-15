package com.qraccess.daos.imp;

import com.qraccess.daos.interfaces.AccessDao;
import com.qraccess.entities.Access;
import com.qraccess.entities.Admin;

public class AccessDaoImpl extends CrudDaoImpl<Access> implements AccessDao {

	@Override
	public void validate(int id, String hash) {
		// TODO Auto-generated method stub
		
	}
	
	public Class<Access> getEntityClass(){
		return Access.class;
	}
	
	public Access getById(int id) {
		return null;
	}
	
	public AccessDaoImpl(){}
	
	public AccessDaoImpl(Access a){
		this.insert(a);
	}
	
	public static void createAccess(Access a) {
		new AccessDaoImpl(a);
	}
}
