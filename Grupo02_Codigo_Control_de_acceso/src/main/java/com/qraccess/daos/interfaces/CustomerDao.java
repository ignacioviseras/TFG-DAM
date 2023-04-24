package com.qraccess.daos.interfaces;
import com.qraccess.entities.Customer;

public interface CustomerDao extends CrudDao<Customer>{
	public String showQr();
}
