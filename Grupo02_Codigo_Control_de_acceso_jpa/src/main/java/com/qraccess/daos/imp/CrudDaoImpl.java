package com.qraccess.daos.imp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
			//em.close();
		}
		return obj;
	}	

	public abstract Class<T> getEntityClass();
	
	public abstract T updateEntity(T entity, T updates);

	public T getById(int id) {
		T result = (T) em.find(this.getEntityClass(),id);
		//em.close();
		return result;
	}

	public T update(int id, T updates) {
		try {
			em.getTransaction().begin();
			em.merge(this.updateEntity(this.getById(id), updates));
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}
	public boolean delete(int id) {
		try {
			T obj = this.getById(id); 
			em.getTransaction().begin();
			em.remove(obj);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			//em.close();
		}
		return true;
	}
}
