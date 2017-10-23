package com.edusasse.app.facade;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.edusasse.app.dto.ParametroDTO;
import com.edusasse.app.entity.Parametro;
import com.edusasse.app.persistence.IOperationsFacade;

public interface IParametroFacade extends IOperationsFacade<ParametroDTO, Long> {
	
	ParametroDTO retrieveByName(String name);

	PageDTO<ParametroDTO> findPaginated(Pageable pageable);

	List<ParametroDTO> findAllByCriteria(String search);

	List<ParametroDTO> findAllBySpecification(Specification<Parametro> spec);

}