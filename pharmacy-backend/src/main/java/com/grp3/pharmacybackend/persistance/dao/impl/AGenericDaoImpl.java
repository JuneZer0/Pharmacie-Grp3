package com.grp3.pharmacybackend.persistance.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.grp3.pharmacybackend.persistance.dao.interfaces.IGenericDao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
public abstract class AGenericDaoImpl <T> implements IGenericDao<T>{

    private Class<T> objDo;

    @Autowired
    private SessionFactory sessionFactory;

    private  Session session;
    private  Transaction transaction;



    @Override
    @Transactional
    public List<T> findAll(Class class1) {   
        List<T> resultList = new ArrayList<T>();
        Session currentSession = sessionFactory.getCurrentSession();
        Query<T> query = currentSession.createQuery("from "+class1.getName(), class1);   
        resultList = (List<T>) query.getResultList(); 
        return resultList;            
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


   

   




}