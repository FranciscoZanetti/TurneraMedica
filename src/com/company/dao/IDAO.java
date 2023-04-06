package com.company.dao;

import java.util.ArrayList;

public interface IDAO<T> {

    public void create(T object) throws DAOException;
    public void update(T object, int primaryKey) throws DAOException;
    public void delete(int primaryKey) throws DAOException;
    public T search(int primaryKey) throws DAOException;
//    public ArrayList<T> searchAll() throws DAOException;

}
