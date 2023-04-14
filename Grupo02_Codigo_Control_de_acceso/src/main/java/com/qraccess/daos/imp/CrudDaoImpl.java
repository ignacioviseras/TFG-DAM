package com.qraccess.daos.imp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public abstract class CrudDaoImpl<T> {
	final String entity_manager_creator = "QR_ACCESS";
	
	public CrudDaoImpl() {}
	
	public T insert(T obj) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(entity_manager_creator);
		EntityManager em = factory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {			
			et.begin();			
			em.persist(obj);
			et.commit();
		} catch (Exception e) {
			et.rollback();
			e.printStackTrace();
	    } finally {
	    	em.close();
	    	factory.close();
	    }
		return obj;
	}
	public T getById(int id) {
		return null;
	}
	public T update(T obj) {
		return obj;
	}
	public boolean delete(int id) {
		return true;
	}
}
