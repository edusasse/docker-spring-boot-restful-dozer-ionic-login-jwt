package com.edusasse.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edusasse.app.dto.UnidadeFederacaoDTO;
import com.edusasse.app.entity.UnidadeFederacao;
import com.edusasse.app.persistence.dao.repository.IUnidadeFederacaoRepository;
import com.edusasse.app.service.UnidadeFederacaoService;

@Service
public class UnidadeFederacaoServiceImpl implements UnidadeFederacaoService {

	@Autowired
    private IUnidadeFederacaoRepository repository;
    @Autowired
    private DozerBeanMapper mapper;
    
    /*
     * (non-Javadoc)
     * @see com.sasse.service.UnidadeFederacaoDTOService#findAll()
     */
	@Override
	public List<UnidadeFederacaoDTO> findAll() {
		 return repository.findAll().stream()
		            .map(entity -> mapper.map(entity, UnidadeFederacaoDTO.class))
		            .collect(Collectors.toList());
	}

	@Override
	public void save(UnidadeFederacaoDTO enderecoDTO) {
		repository.save(mapper.map(enderecoDTO, UnidadeFederacao.class));
	}

}
