package com.grp3.pharmacybackend.persistance.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.grp3.pharmacybackend.persistance.dao.interfaces.IGenericDao;
import com.grp3.pharmacybackend.persistance.entities.Article;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

@SuppressWarnings("unchecked")
public abstract class AGenericDaoImpl <T> implements IGenericDao<T> {

   
 

    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();   
    private Session session = null;

    @Override
    public List<T> findAll(final Class class1) {  
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
    public Optional<T> findById(final Long idObjDo, final Class class1, final String queryField) {
        Optional<T> article = Optional.empty();
        try { 
            startOperation();
            Query<T> query = session.createQuery("from "+class1.getName()+" a where "+queryField+" = :id");   
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
    public List<T> findAllByNameContaining(final String objDoName, final Class class1, final String queryField) {
        List<T> resultList = new ArrayList<T>();
        try { 
            startOperation();
            Query<T> query = session.createQuery("from "+class1.getName()+" a where "+queryField+" like :name");  
            query.setParameter("name", objDoName);
            resultList = (List<T>) query.getResultList();
            session.getTransaction().commit();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }      
        finally{
           
                closeOperation();
          
        }
        return resultList; 
    }

   

    @Override
<<<<<<< HEAD
    public void save(T objDoToCreate) {
       
            try { 
                startOperation();   
                session.saveOrUpdate(objDoToCreate);
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



    
=======
    public void save(final T objDoToCreate, Long id) {
       try { 
           
        startOperation();

        session.saveOrUpdate(objDoToCreate);
        

        session.getTransaction().commit();
       }
       catch(Exception e){
           System.out.println(e.getMessage());
       }
       finally{
          closeOperation();
       }
    }
>>>>>>> 72a3b4a5d5f99b527153b8613dc1ef5953e30b0f

    @Override
    public void deleteById(final Long idObjDo, final Class class1, final String queryField) {
        try { 
            startOperation();
            Query<T> query = session.createQuery("delete from "+class1.getName()+" a where "+queryField+" = :id");   
            query.setParameter("id", idObjDo);
            query.executeUpdate();
            session.getTransaction().commit();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }      
        finally{
           
                closeOperation();
           
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
        if (session!=null && session.isOpen()){
        session.close();
        session=null;
        }
    }

}