package com.soares.hudson.springcrude.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Component
public class TesteDto {
    @PersistenceContext
    private EntityManager entityManager;

    public Object getCurso(Long id) {
        Query query = entityManager
                .createNativeQuery("Select nome as nome, categoria as categ from cursos where id=?1");
        query.setParameter(1, id);
        return query.getSingleResult();
    }

}
