package com.edusasse.app.facade;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.edusasse.app.dto.UsuarioDTO;
import com.edusasse.app.entity.Usuario;
import com.edusasse.app.persistence.IOperationsFacade;

public interface IUsuarioFacade extends IOperationsFacade<UsuarioDTO, Long> {
	
	UsuarioDTO retrieveByName(String name);

	PageDTO<UsuarioDTO> findPaginated(Pageable pageable);

	List<UsuarioDTO> findAllByCriteria(String search);

	List<UsuarioDTO> findAllBySpecification(Specification<Usuario> spec);

}