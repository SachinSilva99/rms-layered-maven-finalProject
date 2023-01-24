package com.sachin.finalproject.dao;

import com.sachin.finalproject.dao.exception.ConstraintViolationException;
import com.sachin.finalproject.entity.SuperEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CrudDAO<T extends SuperEntity,ID> extends SuperDAO {
    T save(T t) throws ConstraintViolationException;

    T update(T t)throws ConstraintViolationException;

    void deleteByPk(ID pk)throws ConstraintViolationException;

    List<T> findAll() ;

    Optional<T> findByPk(ID pk);

    boolean existByPk(ID pk);

    long count() ;
}
