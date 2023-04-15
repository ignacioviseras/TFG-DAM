package com.qraccess.daos.interfaces;
import com.qraccess.entities.Customer;

public interface UserDao extends CrudDao<Customer>{

	public String showQr();
}
