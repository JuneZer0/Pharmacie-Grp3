package com.grp3.pharmacybackend.persistance.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.grp3.pharmacybackend.persistance.dao.interfaces.IGenericDao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

@SuppressWarnings("unchecked")
public abstract class AGenericDaoImpl <T> implements IGenericDao<T>{

   
    private Class<T> objDo;

    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();   
    private Session session = null;

    @Override
    public List<T> findAll(Class class1) {  
        System.out.println("FINDALL");
        List<T> resultList = new ArrayList<T>();
        try { 
            startOperation();
            Query<T> query = session.createQuery("from "+class1.getName(), class1);   
            resultList = (List<T>) query.getResultList();  
            session.getTransaction().commit();
            }
        catch(Exception e){
            System.out.println(e.getMessage());
        }      
        finally{
            if (session!=null && session.isOpen()){
                closeOperation();
            }
        }
        return resultList;      
    }

    @Override
    public Optional<T> findById(Long idObjDo) {
        System.out.println("FINDONEBYID");
        Optional<T> article = Optional.empty();
        try { 
            startOperation();
            Query<T> query = session.createQuery("from Article a where a.articleId = :id");   
            query.setParameter("id", idObjDo);
            article = query.uniqueResultOptional();  
            session.getTransaction().commit();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }      
        finally{
            if (session!=null && session.isOpen()){
                closeOperation();
            }
        }
        return article;
    }

    @Override
    public List<T> findAllByNameContaining(String objDoName) {
        System.out.println("FIND ALL BY NAME");
        System.out.println(objDoName);
        List<T> resultList = new ArrayList<T>();
        try { 
            startOperation();
            Query<T> query = session.createQuery("from Article a where a.articleName like :name");  
            query.setParameter("name", objDoName);
            resultList = (List<T>) query.getResultList();
            session.getTransaction().commit();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }      
        finally{
            if (session!=null && session.isOpen()){
                closeOperation();
            }
        }
        return resultList; 
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
        try {
            startOperation();
            session.delete(idObjDo);
            session.getTransaction().commit();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }      
        finally{
            if (session!=null && session.isOpen()){
                closeOperation();
            }
        }
    }

    /**
     * Open a session and begin a transaction
     */
    public void startOperation() {
        session = sessionFactory.openSession();  
        session.beginTransaction();
    }

    /**
     * Close the session
     */
    public void closeOperation() {
        session.close();
        session=null;
    }

}