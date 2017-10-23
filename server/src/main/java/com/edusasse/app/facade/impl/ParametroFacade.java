package com.edusasse.app.facade.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.edusasse.app.dto.ParametroDTO;
import com.edusasse.app.entity.Parametro;
import com.edusasse.app.facade.IParametroFacade;
import com.edusasse.app.facade.PageDTO;
import com.edusasse.app.facade.common.AbstractFacade;
import com.edusasse.app.service.IParametroService;
import com.edusasse.app.service.common.AbstractService;

@Service
public class ParametroFacade extends AbstractFacade<ParametroDTO, Parametro, Long> implements IParametroFacade {

	@Autowired
    private DozerBeanMapper mapper;
	
	@Autowired
	private IParametroService service;
	
	@Autowired
	private AbstractService<Parametro, Long> abstractService;
	 
    
    public ParametroFacade() {
        super();
    }

    /*
     * (non-Javadoc)
     * @see com.edusasse.app.facade.IParametroFacade#retrieveByName(java.lang.String)
     */
	@Override
	public ParametroDTO retrieveByName(String name) {
		return getMapper().map(service.findAllByCriteria("parametro="+name), ParametroDTO.class);
	}

	/*
	 * (non-Javadoc)
	 * @see com.edusasse.app.facade.IParametroFacade#findPaginated(org.springframework.data.domain.Pageable)
	 */
	@Override
	public PageDTO<ParametroDTO> findPaginated(Pageable pageable) {
		final Page<Parametro> findPaginated = service.findPaginated(pageable);
		return new PageDTO<ParametroDTO>(findPaginated.getTotalPages(), findPaginated.getContent().stream()
	            .map(entity -> getMapper().map(entity, getDTOClass()))
	            .collect(Collectors.toList()));
	}

	/*
	 * (non-Javadoc)
	 * @see com.edusasse.app.facade.IParametroFacade#findAllByCriteria(java.lang.String)
	 */
	@Override
	public List<ParametroDTO> findAllByCriteria(String search) {
		return service.findAllByCriteria(search).stream()
        .map(entity -> getMapper().map(entity, getDTOClass()))
        .collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 * @see com.edusasse.app.facade.IParametroFacade#findAllBySpecification(org.springframework.data.jpa.domain.Specification)
	 */
	@Override
	public List<ParametroDTO> findAllBySpecification(Specification<Parametro> spec) {
		return service.findAllBySpecification(spec).stream()
		        .map(entity -> getMapper().map(entity, getDTOClass()))
		        .collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 * @see com.edusasse.app.facade.common.AbstractFacade#getService()
	 */
	@Override	
	protected AbstractService<Parametro, Long> getService() {
		return abstractService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.edusasse.app.facade.common.AbstractFacade#getMapper()
	 */
	@Override
	protected DozerBeanMapper getMapper() {
		return this.mapper;
	}
  
}