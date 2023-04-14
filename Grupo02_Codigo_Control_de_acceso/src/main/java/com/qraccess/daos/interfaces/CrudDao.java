package com.qraccess.daos.interfaces;

public interface CrudDao<T> {
    public T insert(T obj);
    public T getById(int id);
    public T update(T obj);
    public boolean delete(int id);
}