package com.qraccess.daos.imp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public abstract class CrudDaoImpl<T> {
	final String entity_manager_creator = "QR_ACCESS";
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory(entity_manager_creator);
	private EntityManager em = emf.createEntityManager();
	public CrudDaoImpl() {}
	
	public T insert(T obj) {		
		try {			
			em.getTransaction().begin();			
			em.persist(obj);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
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
