package com.edusasse.app.persistence.dao;

import java.util.List;

import com.edusasse.app.entity.Usuario;
import com.edusasse.app.web.util.SearchCriteria;

public interface IUsuarioDao {
	
    List<Usuario> searchUsuario(List<SearchCriteria> params);

    void save(Usuario entity);
}
