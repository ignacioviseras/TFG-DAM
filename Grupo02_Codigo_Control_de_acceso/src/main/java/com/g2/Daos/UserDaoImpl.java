package com.g2.Daos;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.g2.Daos.Intf.IntUserDao;
import com.g2.Entities.User;

public class UserDaoImpl implements IntUserDao{

	EntityManagerFactory factoria = Persistence.createEntityManagerFactory("QR_ACCESS");
	EntityManager em = factoria.createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	@Override
	public String showQr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User create(User user) {
		Query query = em.createQuery("INSERT INTO users (name, password, mail) VALUES (:name, :password, :mail)");
		
		query.setParameter("name", user.getName());
		query.setParameter("password", user.getPassword());
		query.setParameter("mail", user.getMail());
		
		query.executeUpdate();
		user.setId((int) query.getSingleResult());
		et.commit();
		return user;
	}

	@Override
	public User getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
