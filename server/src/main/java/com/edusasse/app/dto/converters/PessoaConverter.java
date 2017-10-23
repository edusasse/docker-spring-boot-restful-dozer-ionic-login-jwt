package com.edusasse.app.dto.converters;

import org.dozer.DozerBeanMapper;
import org.dozer.DozerConverter;

import com.edusasse.app.controller.ApplicationContextProvider;
import com.edusasse.app.dto.PessoaFisicaDTO;
import com.edusasse.app.entity.Pessoa;
import com.edusasse.app.persistence.dao.repository.IPessoaRepository;

public class PessoaConverter extends DozerConverter<Pessoa, PessoaFisicaDTO> {

	private IPessoaRepository pessoaRepository;
	private DozerBeanMapper mapper;

	public PessoaConverter() {
		super(Pessoa.class, PessoaFisicaDTO.class);
		pessoaRepository = ApplicationContextProvider.getBean(IPessoaRepository.class);
		mapper = ApplicationContextProvider.getBean(DozerBeanMapper.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dozer.DozerConverter#convertTo(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public PessoaFisicaDTO convertTo(Pessoa source, PessoaFisicaDTO destination) {
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
	public Pessoa convertFrom(PessoaFisicaDTO source, Pessoa destination) {
		Pessoa result = null;
		if (source == null) {
			result = null;
		} else {
			if (source.getId() < 0) {
				result = pessoaRepository.save(mapper.map(source, Pessoa.class));
			} else {
				final Pessoa cidade = pessoaRepository.findOne(source.getId());
				if (cidade != null) {
					result = cidade;
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
