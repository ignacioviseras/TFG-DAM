package com.qraccess.daos.interfaces;

import com.qraccess.entities.Access;
import com.qraccess.entities.Admin;

public interface AccessDao extends CrudDao<Access>{

	public void validate(int id, String hash);
	
	public Access updateEntity(Access entity, Access updates) {
		entity.setAvailables(updates.getAvailables());
		entity.setExpires(updates.getExpires());
		entity.setUuid(updates.getUuid());
		return entity;
	}
}
