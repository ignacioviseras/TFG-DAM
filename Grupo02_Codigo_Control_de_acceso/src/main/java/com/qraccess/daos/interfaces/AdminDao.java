package com.qraccess.daos.interfaces;
import com.qraccess.entities.Access;
import com.qraccess.entities.Admin;
import com.qraccess.entities.User;

public interface AdminDao extends CrudDao<Admin>{

	public String createAccess(Access a);
	public String createUser(User u);
	
}
