package com.qraccess.daos.interfaces;

import com.qraccess.entities.Access;

public interface AccessDao extends CrudDao<Access>{
	void validate(int id);
	void renew(int id, long timestamp);
	void addValidations(int id, int n);
}
