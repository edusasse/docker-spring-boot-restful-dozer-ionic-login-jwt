package com.edusasse.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edusasse.app.dto.TelefoneDTO;
import com.edusasse.app.entity.Telefone;
import com.edusasse.app.persistence.dao.repository.ITelefoneRepository;
import com.edusasse.app.service.TelefoneService;

@Service
public class TelefoneServiceImpl implements TelefoneService {

	@Autowired
    private ITelefoneRepository repository;
    @Autowired
    private DozerBeanMapper mapper;
    
    /*
     * (non-Javadoc)
     * @see com.sasse.service.TelefoneDTOService#findAll()
     */
	@Override
	public List<TelefoneDTO> findAll() {
		 return repository.findAll().stream()
		            .map(entity -> mapper.map(entity, TelefoneDTO.class))
		            .collect(Collectors.toList());
	}

	/*
	 * (non-Javadoc)
	 * @see com.sasse.service.TelefoneDTOService#findByTelefoneDTOname(java.lang.String)
	 */
	@Override
	public TelefoneDTO findByNumeroTelefone(String numTelefone) {
		TelefoneDTO result = null;
		if (numTelefone != null){
			final Telefone t = repository.findByNumeroTelefone(numTelefone);
			if (t != null){
				result = mapper.map(t, TelefoneDTO.class);
			}
		}
		
		return result;
	}
	
	@Override
	public void save(TelefoneDTO usuarioDTO) {
		repository.save(mapper.map(usuarioDTO, Telefone.class));
	}

}
