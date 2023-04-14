package com.qraccess.daos.imp;

import javax.persistence.Query;

import com.qraccess.daos.interfaces.AccessDao;
import com.qraccess.entities.Access;

public class AccessDaoImpl extends CrudDaoImpl<Access> implements AccessDao {

	@Override
	public void validate(int id, String hash) {
		// TODO Auto-generated method stub
		
	}
	
	public Access getById(int id) {
		return null;
	}


}
