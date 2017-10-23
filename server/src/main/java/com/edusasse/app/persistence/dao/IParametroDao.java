package com.edusasse.app.persistence.dao;

import java.util.List;

import com.edusasse.app.entity.Parametro;
import com.edusasse.app.web.util.SearchCriteria;

public interface IParametroDao {
	
    List<Parametro> searchParametro(List<SearchCriteria> params);

    void save(Parametro entity);
}
