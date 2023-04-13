package com.qraccess.daos.interfaces;
import com.qraccess.entities.Admin;

public interface AdminDao extends CrudDao<Admin>{

	public String generateQr();
	
}
