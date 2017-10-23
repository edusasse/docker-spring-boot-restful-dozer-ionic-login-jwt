package com.edusasse.app.dto.converters;

import org.dozer.DozerBeanMapper;
import org.dozer.DozerConverter;

import com.edusasse.app.controller.ApplicationContextProvider;
import com.edusasse.app.dto.CidadeDTO;
import com.edusasse.app.entity.Cidade;
import com.edusasse.app.persistence.dao.repository.ICidadeRepository;

public class CidadeConverter extends DozerConverter<Cidade, CidadeDTO> {

	private ICidadeRepository cidadeRepository;
	private DozerBeanMapper mapper;

	public CidadeConverter() {
		super(Cidade.class, CidadeDTO.class);
		cidadeRepository = ApplicationContextProvider.getBean(ICidadeRepository.class);
		mapper = ApplicationContextProvider.getBean(DozerBeanMapper.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dozer.DozerConverter#convertTo(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public CidadeDTO convertTo(Cidade source, CidadeDTO destination) {
		if (source == null) {
			return null;
		} else {
			return mapper.map(source, CidadeDTO.class);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dozer.DozerConverter#convertFrom(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public Cidade convertFrom(CidadeDTO source, Cidade destination) {
		Cidade result = null;
		if (source == null) {
			result = null;
		} else {
			if (source.getId() < 0) {
				result = cidadeRepository.save(mapper.map(source, Cidade.class));
			} else {
				final Cidade cidade = cidadeRepository.findOne(source.getId());
				if (cidade != null) {
					result = cidade;
				} else {
					// Unexpected situation
					throw new RuntimeException(String.format(
							"Cidade com o ID [%d] não encontrada no banco de dados. Erro inesperado.", source.getId()));
				}
			}
		}
		return result;
	}
}
