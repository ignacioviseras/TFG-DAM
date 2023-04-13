package com.qraccess.daos.imp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public abstract class DBManager<T> {
	private EntityManagerFactory factory;
	final String entity_manager_creator = "QR_ACCESS";
	
	public DBManager() {
		this.factory = Persistence.createEntityManagerFactory(entity_manager_creator);
	}
	
	public T create(T obj) {
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
	
	public Object getById(int id) {
		return null;
	}
}
