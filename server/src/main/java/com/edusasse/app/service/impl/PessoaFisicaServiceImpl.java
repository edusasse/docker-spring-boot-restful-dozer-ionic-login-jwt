package com.edusasse.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import com.edusasse.app.entity.PessoaFisica;
import com.edusasse.app.persistence.dao.IPessoaFisicaDao;
import com.edusasse.app.persistence.dao.repository.IPessoaFisicaRepository;
import com.edusasse.app.service.IPessoaFisicaService;
import com.edusasse.app.service.common.AbstractService;

@Service
public class PessoaFisicaServiceImpl extends AbstractService<PessoaFisica, Integer> implements IPessoaFisicaService {

	@Autowired
	private IPessoaFisicaDao dao;
	
    @Autowired
    private IPessoaFisicaRepository repo;

    @Override
	protected PagingAndSortingRepository<PessoaFisica, Integer> getDao() {
		return repo;
	}

}