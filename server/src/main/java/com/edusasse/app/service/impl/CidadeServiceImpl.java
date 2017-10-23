package com.edusasse.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edusasse.app.dto.CidadeDTO;
import com.edusasse.app.entity.Cidade;
import com.edusasse.app.persistence.dao.repository.ICidadeRepository;
import com.edusasse.app.service.CidadeService;

@Service
public class CidadeServiceImpl implements CidadeService {

	@Autowired
    private ICidadeRepository repository;
    @Autowired
    private DozerBeanMapper mapper;
    
    /*
     * (non-Javadoc)
     * @see com.sasse.service.CidadeDTOService#findAll()
     */
	@Override
	public List<CidadeDTO> findAll() {
		 return repository.findAll().stream()
		            .map(entity -> mapper.map(entity, CidadeDTO.class))
		            .collect(Collectors.toList());
	}

	@Override
	public void save(CidadeDTO cidadeDTO) {
		repository.save(mapper.map(cidadeDTO, Cidade.class));
	}

}
