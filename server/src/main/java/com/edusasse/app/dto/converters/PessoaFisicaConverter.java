package com.edusasse.app.dto.converters;

import org.dozer.DozerBeanMapper;
import org.dozer.DozerConverter;

import com.edusasse.app.controller.ApplicationContextProvider;
import com.edusasse.app.dto.PessoaFisicaDTO;
import com.edusasse.app.entity.PessoaFisica;
import com.edusasse.app.persistence.dao.repository.IPessoaFisicaRepository;

public class PessoaFisicaConverter extends DozerConverter<PessoaFisica, PessoaFisicaDTO> {

	private IPessoaFisicaRepository pessoaFisicaRepository;
	private DozerBeanMapper mapper;

	public PessoaFisicaConverter() {
		super(PessoaFisica.class, PessoaFisicaDTO.class);
		pessoaFisicaRepository = ApplicationContextProvider.getBean(IPessoaFisicaRepository.class);
		mapper = ApplicationContextProvider.getBean(DozerBeanMapper.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dozer.DozerConverter#convertTo(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public PessoaFisicaDTO convertTo(PessoaFisica source, PessoaFisicaDTO destination) {
		if (source == null) {
			return null;
		} else {
			return mapper.map(source, PessoaFisicaDTO.class);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dozer.DozerConverter#convertFrom(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public PessoaFisica convertFrom(PessoaFisicaDTO source, PessoaFisica destination) {
		PessoaFisica result = null;
		if (source == null) {
			result = null;
		} else {
			if (source.getId() == null || (source.getId() != null && source.getId() < 0)) {
				result = pessoaFisicaRepository.save(mapper.map(source, PessoaFisica.class));
			} else {
				final PessoaFisica pessoaFisica = pessoaFisicaRepository.findOne(source.getId());
				if (pessoaFisica != null) {
					result = pessoaFisica;
				} else {
					// Unexpected situation
					throw new RuntimeException(String.format(
							"Pessoa com o ID [%d] não encontrada no banco de dados. Erro inesperado.", source.getId()));
				}
			}
		}
		return result;
	}
}
