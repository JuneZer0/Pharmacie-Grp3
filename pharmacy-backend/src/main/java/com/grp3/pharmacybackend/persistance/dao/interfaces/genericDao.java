package com.grp3.pharmacybackend.persistance.dao.interfaces;

import java.util.List;

/**
* This interface is a generic Dao interface that contains CRUD methods.
* To use this method for a specific object type, use a new interface that extends this one and specify the object type instead of T and I.
*/
public interface GenericDao <T> {

    public List<T> list();    
    public T findByName(String articleName);
    public List<T>listByName();
    public T save(T objDo); 
    public void delete(Long idObjDo); 
    public T edit(T objDoToUpdate);


} 
    
}
