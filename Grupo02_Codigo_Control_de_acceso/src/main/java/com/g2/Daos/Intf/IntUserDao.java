package com.g2.Daos.Intf;
import com.g2.Entities.User;

public interface IntUserDao extends IntCrudDao<User>{

	public String showQr();
}
