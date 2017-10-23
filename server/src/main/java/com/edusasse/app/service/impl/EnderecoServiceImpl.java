package com.edusasse.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edusasse.app.dto.EnderecoDTO;
import com.edusasse.app.entity.Endereco;
import com.edusasse.app.persistence.dao.repository.IEnderecoRepository;
import com.edusasse.app.service.EnderecoService;

@Service
public class EnderecoServiceImpl implements EnderecoService {

	@Autowired
    private IEnderecoRepository repository;
    @Autowired
    private DozerBeanMapper mapper;
    
    /*
     * (non-Javadoc)
     * @see com.sasse.service.EnderecoDTOService#findAll()
     */
	@Override
	public List<EnderecoDTO> findAll() {
		 return repository.findAll().stream()
		            .map(entity -> mapper.map(entity, EnderecoDTO.class))
		            .collect(Collectors.toList());
	}

	@Override
	public void save(EnderecoDTO enderecoDTO) {
		repository.save(mapper.map(enderecoDTO, Endereco.class));
	}

}
