package com.qraccess.daos.interfaces;
import com.qraccess.entities.User;

public interface UserDao extends CrudDao<User>{

	public String showQr();
}
