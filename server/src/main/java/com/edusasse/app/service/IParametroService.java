package com.edusasse.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.edusasse.app.entity.Parametro;
import com.edusasse.app.persistence.IOperationsService;

public interface IParametroService extends IOperationsService<Parametro, Long> {
 
    Page<Parametro> findPaginated(Pageable pageable);
    
    List<Parametro> findAllByCriteria(String search);
    
    List<Parametro> findAllBySpecification(Specification<Parametro> spec);

}
