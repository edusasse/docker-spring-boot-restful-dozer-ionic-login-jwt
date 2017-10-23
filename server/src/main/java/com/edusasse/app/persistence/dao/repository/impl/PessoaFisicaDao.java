package com.edusasse.app.persistence.dao.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.edusasse.app.entity.PessoaFisica;
import com.edusasse.app.persistence.dao.IPessoaFisicaDao;

@Repository
public class PessoaFisicaDao implements IPessoaFisicaDao {

    @PersistenceContext
    private EntityManager entityManager;
    
    /*
     * (non-Javadoc)
     * @see com.edusasse.app.persistence.dao.IPessoaFisicaDao#save(com.edusasse.app.entity.PessoaFisica)
     */
    @Override
    public void save(final PessoaFisica entity) {
        entityManager.persist(entity);
    }

}