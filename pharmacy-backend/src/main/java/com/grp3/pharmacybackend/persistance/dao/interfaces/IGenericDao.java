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
     * Get a T object by its name
     * @param objDoName the name of the object to retrieve
     * @return an Optional of type T
     */ 
    public Optional<T> findByName(final String objDoName);


    /**
     * Gets a T object from the database using its ID to find it.
     * @param idObjDo the id of the object we want to find
     * @return an Optional of type T that has the provided id.
     */
    public Optional<T> findById(final Long idObjDo);


    /**
     * @param objDoName the name of the objects to retrieve
     * @return a List of T objects containing the given name
     */
    public List<T>findAllByNameContaining(final String objDoName);

    /**
     * Creates a T object in the database
     * @param objDoToCreate the T object to create in the database
     */
    public void save(final T objDoToCreate); 
    
    /**
     * Update a T object in the database.
     * @param objDoToUpdate the object to update in the database
     */
    public void update(final T objDoToUpdate);


    /**
     * Delete a T object in the database using its ID.
     * @param idObjDo the id of the T object to delete from the database.
     */
    public void deleteById(final Long idObjDo);

    /**
     * Open a session and begin a transaction
     */
    public void startOperation();

    /**
     * Close the session
     */
    public void closeOperation();

} 
    
