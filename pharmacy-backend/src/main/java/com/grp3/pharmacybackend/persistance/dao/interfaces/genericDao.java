package com.grp3.pharmacybackend.persistance.dao.interfaces;

public interface genericDao {

    public T save(T article); 
    public Boolean delete(T article); 
    public T edit(T article);
    public T find(Long articleName); } 
    
}
