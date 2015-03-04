package br.com.ifit.io.dao;


import br.com.ifit.exception.DAOException;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.ifit.io.PersistenceUtil;
import br.com.ifit.io.dao.ExceptionsType;

public abstract class DaoGenericImpl<T> implements DaoGeneric<T> {

    private Class classe;

    public DaoGenericImpl() {
        this.classe = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    @Transactional
    public T save(T t) throws DAOException {
        EntityManager manager = PersistenceUtil.getEntityManager();
        try {
            manager.persist(t);
            manager.flush();
            manager.refresh(t);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(ExceptionsType.getMessage(ExceptionsType.INSERT_ERROR), e);
        }
    }

    @Override
    public T update(T t) throws DAOException {
        EntityManager manager = PersistenceUtil.getEntityManager();
        try {
            manager.merge(t);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(ExceptionsType.getMessage(ExceptionsType.UPDATE_ERROR), e);
        }
    }

    @Override
    public T remove(T t) throws DAOException {
        EntityManager manager = PersistenceUtil.getEntityManager();
        try {
            t = manager.merge(t);
            manager.remove(t);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(ExceptionsType.getMessage(ExceptionsType.REMOVE_ERROR), e);
        }
    }

    @Override
    public T merge(T t) throws DAOException {
        EntityManager manager = PersistenceUtil.getEntityManager();
        try {
            return manager.merge(t);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(ExceptionsType.getMessage(ExceptionsType.REMOVE_ERROR), e);
        }
    }

    @Override
    public T getById(Long id) throws DAOException {
        T resultado = null;
        EntityManager manager = PersistenceUtil.getEntityManager();
        try {
            Session session = (Session) manager.getDelegate();
            Criteria criteria = session.createCriteria(classe);
            criteria.add(Restrictions.eq("id", id));
            resultado = (T) criteria.uniqueResult();
            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(ExceptionsType.getMessage(ExceptionsType.READ_ERROR), e);
        }
    }

    @Override
    public Integer count(String status) throws DAOException {
        Integer resultado = null;
        EntityManager manager = PersistenceUtil.getEntityManager();
        try {
            Session session = (Session) manager.getDelegate();
            Criteria criteria = session.createCriteria(classe);
            if (status != null) {
                criteria.add(Restrictions.eq("status", status));
            }
            resultado = ((Number) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(ExceptionsType.getMessage(ExceptionsType.READ_ERROR), e);
        }
        return resultado;
    }

    @Override
    public List<T> getAllOrderByAsc(String arg) throws DAOException {
        List<T> resultado = null;
        EntityManager manager = PersistenceUtil.getEntityManager();
        try {
            Session session = (Session) manager.getDelegate();
            Criteria criteria = session.createCriteria(classe);
            criteria.addOrder(Order.asc(arg));
            resultado = (List<T>) criteria.list();
            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(ExceptionsType.getMessage(ExceptionsType.READ_ERROR), e);
        }
    }

    @Override
    public List<T> getAllOrderByDesc(String arg) throws DAOException {
        List<T> resultado = null;
        EntityManager manager = PersistenceUtil.getEntityManager();
        try {
            Session session = (Session) manager.getDelegate();
            Criteria criteria = session.createCriteria(classe);
            criteria.addOrder(Order.desc(arg));
            resultado = (List<T>) criteria.list();
            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(ExceptionsType.getMessage(ExceptionsType.READ_ERROR), e);
        }
    }

    @Override
    public List<T> getAll() throws DAOException {
        List<T> resultado = null;
        EntityManager manager = PersistenceUtil.getEntityManager();
        try {
            Session session = (Session) manager.getDelegate();
            resultado = (List<T>) session.createCriteria(classe).list();
            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(ExceptionsType.getMessage(ExceptionsType.READ_ERROR), e);
        }
    }
    
    @Override
    public List<T> getUltimos() throws DAOException {
        List<T> resultado = null;
        EntityManager manager = PersistenceUtil.getEntityManager();
        try {
            Session session = (Session) manager.getDelegate();
            Criteria criteria = session.createCriteria(classe);
            criteria.addOrder(Order.desc("dataCadastro"));
            criteria.setFirstResult(0).setMaxResults(10);
            resultado = (List<T>) criteria.list();
            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(ExceptionsType.getMessage(ExceptionsType.READ_ERROR), e);
        }
    }

    @Override
    public Criteria getCriteria() {
        EntityManager manager = PersistenceUtil.getEntityManager();
        return ((Session) manager.getDelegate()).createCriteria(classe);
    }

    @Override
    public Session getSession() {
        EntityManager manager = PersistenceUtil.getEntityManager();
        return ((Session) manager.getDelegate());
    }

    @Override
    public T executeNativeQuerySingleResult(String sql) throws DAOException {
        T resultado = null;
        EntityManager manager = PersistenceUtil.getEntityManager();
        try {
            Query query = manager.createNativeQuery(sql, classe);
            resultado = (T) query.getSingleResult();
            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(ExceptionsType.getMessage(ExceptionsType.READ_ERROR), e);
        }
    }

    @Override
    public List<T> executeNativeQueryMultiResult(String sql) throws DAOException {
        List<T> resultado = null;
        EntityManager manager = PersistenceUtil.getEntityManager();
        try {
            Query query = manager.createNativeQuery(sql, classe);
            resultado = (List<T>) query.getResultList();
            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(ExceptionsType.getMessage(ExceptionsType.READ_ERROR), e);
        }
    }
}