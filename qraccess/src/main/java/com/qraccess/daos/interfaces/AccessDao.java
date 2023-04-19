package com.qraccess.daos.interfaces;

import com.qraccess.entities.Access;
import com.qraccess.entities.Admin;

public interface AccessDao extends CrudDao<Access>{

	public void validate(int id, String hash);	
	
}
