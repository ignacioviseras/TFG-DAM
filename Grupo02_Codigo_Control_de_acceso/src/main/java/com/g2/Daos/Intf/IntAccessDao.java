package com.g2.Daos.Intf;

import com.g2.Entities.Access;

public interface IntAccessDao extends IntCrudDao<Access>{

	public void validate(int id, String hash);
}
