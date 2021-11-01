package com.grp3.pharmacybackend.persistance.dao.interfaces;

import java.util.List;
import java.util.Optional;

/**
* This interface is a generic Dao interface that contains CRUD methods.
* To use this method for a specific object type, use a new interface that extends this one and specify the object type instead of T and I.
* @author Caroline
*/
public interface IGenericDao <T> {

    /**
    * @return a List of T objects from the database.
    */
    public List<T> findAll(Class<T> classT); 

    /**
     * Gets a T object from the database using its ID to find it.
     * @param idObjDo the id of the object we want to find
     * @param class1 the class of the object we want to find
     * @param queryField the name of the field we want to retrieve the object with.
     * @return an Optional of type T that has the provided id.
     */
    public Optional<T> findById(final Long idObjDo, final Class class1, final String queryField);

    /**
     * @param objDoName the name of the objects to retrieve
     * @param class1 the class of the object we want to find
     * @param queryField the name of the field we want to retrieve the object with.
     * @return a List of T objects containing the given name
     */
    public List<T> findAllByNameContaining(final String objDoName, final Class class1, final String queryField);

    /**
     * Creates a T object in the database
     * @param objDoToCreate the T object to create in the database
     */
    public void save(final T objDoToCreate); 
    
   
    /**
     * Delete a T object in the database using its ID.
     * @param idObjDo the id of the T object to delete from the database.
     * @param class1 the class of the object we want to delete
     * @param queryField the name of the field we want to delete the object with.
     */
    public void deleteById(final Long idObjDo, final Class class1, final String queryField);


} 
    
