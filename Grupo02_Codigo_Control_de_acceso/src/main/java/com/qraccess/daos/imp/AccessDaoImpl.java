package com.qraccess.daos.imp;

import javax.persistence.Query;

import com.qraccess.daos.interfaces.AccessDao;
import com.qraccess.entities.Access;

public class AccessDaoImpl extends DBManager<Access> implements AccessDao {
	@Override
	public Access update(Access obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(int id, String hash) {
		// TODO Auto-generated method stub
		
	}
	
	public Access getById(int id) {
		return null;
	}


}
