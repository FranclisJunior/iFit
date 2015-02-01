package br.com.ifit.io.dao;

import br.com.ifit.exception.DAOException;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;

public interface DaoGeneric<T> {
	
    @Transactional
    T save(T t) throws DAOException;

    T update(T t) throws DAOException;

    T remove(T t) throws DAOException;
    
    T merge(T t) throws DAOException;
    
    T getById(Long id) throws DAOException;

    List<T> getAllOrderByAsc(String arg) throws DAOException;
    
    List<T> getAllOrderByDesc(String arg) throws DAOException;
    
    List<T> getAll() throws DAOException;
    
    List<T> getUltimos() throws DAOException;
    
    Integer count(String status) throws DAOException;

    Session getSession();
    
    Criteria getCriteria();
    
    T executeNativeQuerySingleResult(String sql) throws DAOException;
    
    List<T> executeNativeQueryMultiResult(String sql) throws DAOException;
}