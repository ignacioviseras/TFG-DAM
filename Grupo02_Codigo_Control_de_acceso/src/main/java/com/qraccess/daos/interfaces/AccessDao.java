package com.qraccess.daos.interfaces;

import com.qraccess.entities.Access;

public interface AccessDao extends CrudDao<Access>{

	public void validate(int id, String hash);
}
