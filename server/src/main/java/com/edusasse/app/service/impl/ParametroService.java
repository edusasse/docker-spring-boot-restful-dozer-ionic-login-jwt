package com.edusasse.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edusasse.app.entity.Parametro;
import com.edusasse.app.persistence.dao.IParametroDao;
import com.edusasse.app.persistence.dao.repository.IParametroRepository;
import com.edusasse.app.service.IParametroService;
import com.edusasse.app.service.common.AbstractService;
import com.edusasse.app.web.util.SearchCriteriaParser;
import com.google.common.collect.Lists;

@Service
@Transactional
public class ParametroService extends AbstractService<Parametro, Long> implements IParametroService {

	@Autowired
	private IParametroDao dao;
	
    @Autowired
    private IParametroRepository repo;

    
    public ParametroService() {
        super();
    }

    /*
     * (non-Javadoc)
     * @see com.edusasse.app.service.common.AbstractService#getDao()
     */
    @Override
    protected PagingAndSortingRepository<Parametro, Long> getDao() {
        return repo;
    }

    /*
     * (non-Javadoc)
     * @see com.edusasse.app.service.common.AbstractService#findAll()
     */
    @Override
    @Transactional(readOnly = true)
    public List<Parametro> findAll() {
        return Lists.newArrayList(getDao().findAll());
    }

    /*
     * (non-Javadoc)
     * @see com.edusasse.app.service.IParametroService#findPaginated(org.springframework.data.domain.Pageable)
     */
    @Override
    public Page<Parametro> findPaginated(Pageable pageable) {
        return repo.findAll(pageable);
    }

    /*
     * (non-Javadoc)
     * @see com.edusasse.app.service.IParametroService#findAllByCriteria(java.lang.String)
     */
    public List<Parametro> findAllByCriteria(String search) {
        return dao.searchParametro(SearchCriteriaParser.parse(search));
    }
    
    /*
     * (non-Javadoc)
     * @see com.edusasse.app.service.IParametroService#findAllBySpecification(org.springframework.data.jpa.domain.Specification)
     */
    public List<Parametro> findAllBySpecification(Specification<Parametro> spec) {
        return repo.findAll(spec);
    }
    
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Parametro create(Parametro entity) {
    	return super.create(entity);
    }
}