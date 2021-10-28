package com.grp3.pharmacybackend.persistance.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.grp3.pharmacybackend.persistance.dao.interfaces.IGenericDao;
import com.grp3.pharmacybackend.persistance.entities.Article;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.DataException;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AGenericDaoImpl <T extends Serializable> implements IGenericDao<T>{

    private Class<T> objDo;

    @Autowired
    protected SessionFactory sessionFactory;

    private  Session session;
    private  Transaction transaction;



    @Override
    public List<T> findAll(Class class1) {   
        List<T> resultList = new ArrayList<T>();    
        try {
            startOperation();
            Query query = session.createQuery("from" + class1.getName());
            resultList = query.list();            
            transaction.commit();
        } catch (HibernateException e){
            handleException(e)
        }
        
    }

    @Override
    public Optional<T> findByName(String objDoName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<T> findById(Long idObjDo) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List findAllByNameContaining(String objDoName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(T objDoToCreate) {
        return;     
    }

    @Override
    public void update(T objDoToUpdate) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(Long idObjDo) {
        // TODO Auto-generated method stub
        
    }


    /**
     * Opens a session and starts transaction
     * @throws HibernateException
     */
    protected void startOperation() throws HibernateException {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    /**
     * Handles hibernate
     * @param e the exception to handle
     * @throws DataAccessLayerException
     */
    protected void handleException(HibernateException e) throws DataAccessLayerException {
        HibernateFactory.rollback(transaction);
        throw new DataException(e);
    }




}