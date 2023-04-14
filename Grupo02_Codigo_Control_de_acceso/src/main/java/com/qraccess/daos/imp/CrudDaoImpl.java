package com.qraccess.daos.imp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public abstract class CrudDaoImpl<T> {
	private EntityManagerFactory factory;
	final String entity_manager_creator = "QR_ACCESS";
	
	public CrudDaoImpl() {
		this.factory = Persistence.createEntityManagerFactory(entity_manager_creator);
	}
	
	public T insert(T obj) {
		EntityManager em = factory.createEntityManager();
		try {			
			EntityTransaction et = em.getTransaction();
			et.begin();			
			em.persist(obj);
			et.commit();			
		} catch (Exception e) {
			e.printStackTrace();
	    } finally {
	    	em.close();
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
