package com.edusasse.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edusasse.app.dto.ClienteDTO;
import com.edusasse.app.entity.Cliente;
import com.edusasse.app.persistence.dao.repository.IClienteRepository;
import com.edusasse.app.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
    private IClienteRepository repository;
    @Autowired
    private DozerBeanMapper mapper;
    
    /*
     * (non-Javadoc)
     * @see com.sasse.service.ClienteDTOService#findAll()
     */
	@Override
	public List<ClienteDTO> findAll() {
		 return repository.findAll().stream()
		            .map(entity -> mapper.map(entity, ClienteDTO.class))
		            .collect(Collectors.toList());
	}

	@Override
	public void save(ClienteDTO enderecoDTO) {
		repository.save(mapper.map(enderecoDTO, Cliente.class));
	}

}
