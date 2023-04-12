package com.g2.Daos.Intf;
import com.g2.Entities.Admin;

public interface IntAdminDao extends IntCrudDao<Admin>{

	public String generateQr();
	
}
