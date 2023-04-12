package com.g2.Daos;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.g2.Daos.Intf.IntAdminDao;
import com.g2.Entities.Admin;
import com.g2.Entities.User;

public class AdminDaoImpl implements IntAdminDao{

	EntityManagerFactory factoria = Persistence.createEntityManagerFactory("QR_ACCESS");
	EntityManager em = factoria.createEntityManager();
	EntityTransaction et = em.getTransaction();

	@Override
	public String generateQr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin create(Admin admin) {
		Query query = em.createQuery("INSERT INTO admins (name, password, mail) VALUES (:name, :password, :mail)");
		
		query.setParameter("name", admin.getName());
		query.setParameter("password", admin.getPassword());
		query.setParameter("mail", admin.getMail());
		
		query.executeUpdate();
		admin.setId((int) query.getSingleResult());
		et.commit();
		return admin;
	}

	@Override
	public Admin getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

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

}
