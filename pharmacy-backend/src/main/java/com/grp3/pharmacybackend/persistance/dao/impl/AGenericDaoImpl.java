package com.grp3.pharmacybackend.persistance.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import com.grp3.pharmacybackend.persistance.dao.interfaces.IGenericDao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AGenericDaoImpl <T extends Serializable> implements IGenericDao<T>{

    private Class<T> objDo;

    @Autowired
    protected SessionFactory sessionFactory;



    @Override
    public List<T> findAll() {
        // TODO Auto-generated method stub
        return null;
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