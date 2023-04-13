package com.qraccess.daos.imp;


import com.qraccess.daos.interfaces.AdminDao;
import com.qraccess.entities.Admin;

public class AdminDaoImpl extends DBManager<Admin> implements AdminDao {

	@Override
	public Admin update(Admin obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String generateQr() {
		// TODO Auto-generated method stub
		return null;
	}

	public Admin getById(int id) {
		return null;
	}
}
