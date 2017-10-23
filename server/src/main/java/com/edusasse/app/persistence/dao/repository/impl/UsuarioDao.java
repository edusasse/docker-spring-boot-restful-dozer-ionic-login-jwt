package com.edusasse.app.persistence.dao.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.edusasse.app.entity.Usuario;
import com.edusasse.app.persistence.dao.IUsuarioDao;
import com.edusasse.app.web.util.SearchCriteria;

@Repository
public class UsuarioDao implements IUsuarioDao {

    @PersistenceContext
    private EntityManager entityManager;

    /*
     * (non-Javadoc)
     * @see com.edusasse.app.persistence.dao.IUsuarioDao#searchUsuario(java.util.List)
     */
    @Override
    public List<Usuario> searchUsuario(final List<SearchCriteria> params) {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
        final Root r = query.from(Usuario.class);

        Predicate predicate = builder.conjunction();

        for (final SearchCriteria param : params) {
            if (param.getOperation().equalsIgnoreCase(">")) {
                predicate = builder.and(predicate, builder.greaterThanOrEqualTo(r.get(param.getKey()), param.getValue().toString()));
            } else if (param.getOperation().equalsIgnoreCase("<")) {
                predicate = builder.and(predicate, builder.lessThanOrEqualTo(r.get(param.getKey()), param.getValue().toString()));
            } else if (param.getOperation().equalsIgnoreCase(":")) {
                if (r.get(param.getKey()).getJavaType() == String.class) {
                    predicate = builder.and(predicate, builder.like(r.get(param.getKey()), "%" + param.getValue() + "%"));
                } else {
                    predicate = builder.and(predicate, builder.equal(r.get(param.getKey()), param.getValue()));
                }
            }
        }
        query.where(predicate);

        return entityManager.createQuery(query).getResultList();
    }

    /*
     * (non-Javadoc)
     * @see com.edusasse.app.persistence.dao.IUsuarioDao#save(com.edusasse.app.entity.Usuario)
     */
    @Override
    public void save(final Usuario entity) {
        entityManager.persist(entity);
    }

}