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
    private Object ojDoBarcode;

    private Object objDoName;

    private Object objDoQuantity;

    private Object objDoPrice;

    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();   
    private Session session = null;

    @Override
    public List<T> findAll(Class class1) {  
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
        
        
            try { 
                startOperation();
                Query<T> query = session.createQuery("INSERT INTO Article article_barcode= :barcode, article_name= :, article_quantity= :quantity ,article_price= :price"  + objDoToCreate); 
		        query.setParameter("barcode", ojDoBarcode);  
		        query.setParameter("name", objDoName);  
		        query.setParameter("quantity", objDoQuantity);  
		        query.setParameter("price", objDoPrice); 
                int result = query.executeUpdate(); 
                session.save(result);
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

    @Override
    public void update(T objDoToUpdate) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(Long idObjDo) {
        try { 
            startOperation();
            Query<T> query = session.createQuery("delete from Article a where a.articleId = :id");   
            query.setParameter("id", idObjDo);
            query.executeUpdate();
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