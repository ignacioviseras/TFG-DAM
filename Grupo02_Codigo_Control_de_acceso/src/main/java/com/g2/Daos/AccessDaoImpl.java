package com.g2.Daos;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.g2.Daos.Intf.IntAccessDao;
import com.g2.Entities.Access;
import com.g2.Entities.Admin;
import com.g2.Entities.User;

public class AccessDaoImpl implements IntAccessDao{

	EntityManagerFactory factoria = Persistence.createEntityManagerFactory("QR_ACCESS");
	EntityManager em = factoria.createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	@Override
	public void validate(int id, String hash) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Access create(Access access, Admin admin, User user) {
		
		Query query = em.createQuery("INSERT INTO accesses (ace, password, mail) VALUES (:name, :password, :mail)");
		
		query.setParameter("name", access.getAdmin());
		query.setParameter("password", access.getPassword());
		query.setParameter("mail", access.getMail());
		
		query.executeUpdate();
		access.setId((int) query.getSingleResult());
		et.commit();
		return access;
	}

	@Override
	public Access getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Access update(Access obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
