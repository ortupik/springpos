package com.springpos.generic;

import java.util.List;

public interface GenericService<T extends Object> {

    T save(T entity);

    T update(T entity);

    void delete(T entity);

    void delete(int id);

    void deleteInBatch(List<T> entities);

    T find(int id);

    List<T> findAll();
}
