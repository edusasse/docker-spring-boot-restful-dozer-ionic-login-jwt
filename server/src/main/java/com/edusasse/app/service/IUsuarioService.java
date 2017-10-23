package com.edusasse.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.edusasse.app.entity.Usuario;
import com.edusasse.app.persistence.IOperationsService;

public interface IUsuarioService extends IOperationsService<Usuario, Long> {

    Usuario retrieveByName(String name);
    
    Page<Usuario> findPaginated(Pageable pageable);
    
    List<Usuario> findAllByCriteria(String search);
    
    List<Usuario> findAllBySpecification(Specification<Usuario> spec);

}
