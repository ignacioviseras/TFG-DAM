package com.g2.Daos.Intf;

public interface IntCrudDao<T> {
    public T create(T obj);
    public T getById(int id);
    public T update(T obj);
    public boolean delete(int id);
}