package com.edusasse.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edusasse.app.dto.FeriadoDTO;
import com.edusasse.app.entity.Feriado;
import com.edusasse.app.persistence.dao.repository.IFeriadoRepository;
import com.edusasse.app.service.FeriadoService;

@Service
public class FeriadoServiceImpl implements FeriadoService {

	@Autowired
    private IFeriadoRepository repository;
    @Autowired
    private DozerBeanMapper mapper;
    
    /*
     * (non-Javadoc)
     * @see com.sasse.service.FeriadoDTOService#findAll()
     */
	@Override
	public List<FeriadoDTO> findAll() {
		 return repository.findAll().stream()
		            .map(entity -> mapper.map(entity, FeriadoDTO.class))
		            .collect(Collectors.toList());
	}

	@Override
	public void save(FeriadoDTO feriadoaDTO) {
		repository.save(mapper.map(feriadoaDTO, Feriado.class));
	}

}
