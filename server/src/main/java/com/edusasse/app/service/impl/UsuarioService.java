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

import com.edusasse.app.entity.Usuario;
import com.edusasse.app.persistence.dao.IUsuarioDao;
import com.edusasse.app.persistence.dao.repository.IUsuarioRepository;
import com.edusasse.app.service.IPessoaFisicaService;
import com.edusasse.app.service.IUsuarioService;
import com.edusasse.app.service.common.AbstractService;
import com.edusasse.app.web.util.SearchCriteriaParser;
import com.google.common.collect.Lists;

@Service
@Transactional
public class UsuarioService extends AbstractService<Usuario, Long> implements IUsuarioService {

	@Autowired
	private IUsuarioDao dao;
	
    @Autowired
    private IUsuarioRepository repo;

    @Autowired
    private IPessoaFisicaService pessoaFisicaService;
    
    public UsuarioService() {
        super();
    }

    /*
     * (non-Javadoc)
     * @see com.edusasse.app.service.common.AbstractService#getDao()
     */
    @Override
    protected PagingAndSortingRepository<Usuario, Long> getDao() {
        return repo;
    }

    /*
     * (non-Javadoc)
     * @see com.edusasse.app.service.IUsuarioService#retrieveByName(java.lang.String)
     */
    @Override
    public Usuario retrieveByName(final String name) {
    	return repo.retrieveByName(name);
    }

    /*
     * (non-Javadoc)
     * @see com.edusasse.app.service.common.AbstractService#findAll()
     */
    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Usuario> findAll() {
        return Lists.newArrayList(getDao().findAll());
    }

    /*
     * (non-Javadoc)
     * @see com.edusasse.app.service.IUsuarioService#findPaginated(org.springframework.data.domain.Pageable)
     */
    @Override
    public Page<Usuario> findPaginated(Pageable pageable) {
        return repo.findAll(pageable);
    }

    /*
     * (non-Javadoc)
     * @see com.edusasse.app.service.IUsuarioService#findAllByCriteria(java.lang.String)
     */
    public List<Usuario> findAllByCriteria(String search) {
        return dao.searchUsuario(SearchCriteriaParser.parse(search));
    }
    
    /*
     * (non-Javadoc)
     * @see com.edusasse.app.service.IUsuarioService#findAllBySpecification(org.springframework.data.jpa.domain.Specification)
     */
    public List<Usuario> findAllBySpecification(Specification<Usuario> spec) {
        return repo.findAll(spec);
    }
    
    @Override
    public Usuario create(Usuario entity) {
    	pessoaFisicaService.create(entity.getPessoaFisica());
    	return super.create(entity);
    }
}