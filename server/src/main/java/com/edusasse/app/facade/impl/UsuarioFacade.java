package com.edusasse.app.facade.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.edusasse.app.dto.UsuarioDTO;
import com.edusasse.app.dto.enums.TipoDocumento;
import com.edusasse.app.dto.sub.DocumentoDTO;
import com.edusasse.app.entity.Usuario;
import com.edusasse.app.facade.IUsuarioFacade;
import com.edusasse.app.facade.PageDTO;
import com.edusasse.app.facade.common.AbstractFacade;
import com.edusasse.app.service.IUsuarioService;
import com.edusasse.app.service.common.AbstractService;

@Service
public class UsuarioFacade extends AbstractFacade<UsuarioDTO, Usuario, Long> implements IUsuarioFacade {

	@Autowired
    private DozerBeanMapper mapper;
	
	@Autowired
	private IUsuarioService service;
	
	@Autowired
	private AbstractService<Usuario, Long> abstractService;
	 
    
    public UsuarioFacade() {
        super();
    }

    /*
     * (non-Javadoc)
     * @see com.edusasse.app.facade.IUsuarioFacade#retrieveByName(java.lang.String)
     */
	@Override
	public UsuarioDTO retrieveByName(String name) {
		UsuarioDTO result =null;
		
		final Usuario usuario = service.retrieveByName(name);
		if (usuario != null) {
			result = getMapper().map(usuario, UsuarioDTO.class);
		} 
		
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see com.edusasse.app.facade.IUsuarioFacade#findPaginated(org.springframework.data.domain.Pageable)
	 */
	@Override
	public PageDTO<UsuarioDTO> findPaginated(Pageable pageable) {
		final Page<Usuario> findPaginated = service.findPaginated(pageable);
		return new PageDTO<UsuarioDTO>(findPaginated.getTotalPages(), findPaginated.getContent().stream()
	            .map(entity -> getMapper().map(entity, getDTOClass()))
	            .collect(Collectors.toList()));
	}

	/*
	 * (non-Javadoc)
	 * @see com.edusasse.app.facade.IUsuarioFacade#findAllByCriteria(java.lang.String)
	 */
	@Override
	public List<UsuarioDTO> findAllByCriteria(String search) {
		return service.findAllByCriteria(search).stream()
        .map(entity -> getMapper().map(entity, getDTOClass()))
        .collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 * @see com.edusasse.app.facade.IUsuarioFacade#findAllBySpecification(org.springframework.data.jpa.domain.Specification)
	 */
	@Override
	public List<UsuarioDTO> findAllBySpecification(Specification<Usuario> spec) {
		return service.findAllBySpecification(spec).stream()
		        .map(entity -> getMapper().map(entity, getDTOClass()))
		        .collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 * @see com.edusasse.app.facade.common.AbstractFacade#getService()
	 */
	@Override	
	protected AbstractService<Usuario, Long> getService() {
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
  
	@Override
	public UsuarioDTO create(UsuarioDTO entity) {
		// TODO remove/adjust, only here to avoid complexity on signup
		// -------------------------- 
		entity.setPerfil("User");
		entity.getPessoa().setDocumento(new DocumentoDTO("11111111", TipoDocumento.CPF));
		entity.getPessoa().setDataNascimento(new Date());
		entity.getPessoa().seteMail("email@email.com");
		// --------------------------
		return super.create(entity);
	}
}