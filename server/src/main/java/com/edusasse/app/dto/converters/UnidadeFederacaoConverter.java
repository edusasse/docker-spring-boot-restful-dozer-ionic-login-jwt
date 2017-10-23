package com.edusasse.app.dto.converters;

import org.dozer.DozerBeanMapper;
import org.dozer.DozerConverter;

import com.edusasse.app.controller.ApplicationContextProvider;
import com.edusasse.app.entity.UnidadeFederacao;
import com.edusasse.app.persistence.dao.repository.IUnidadeFederacaoRepository;

public class UnidadeFederacaoConverter extends DozerConverter<UnidadeFederacao, String> {

	private IUnidadeFederacaoRepository unidadeFederacaoRepository;
	private DozerBeanMapper mapper;

    public UnidadeFederacaoConverter() {
        super(UnidadeFederacao.class, String.class);
        unidadeFederacaoRepository = ApplicationContextProvider.getBean(IUnidadeFederacaoRepository.class);
        mapper = ApplicationContextProvider.getBean(DozerBeanMapper.class);
    }

    /*
     * (non-Javadoc)
     * @see org.dozer.DozerConverter#convertTo(java.lang.Object, java.lang.Object)
     */
    @Override
    public String convertTo(UnidadeFederacao source, String destination) {
        if (source == null) {
            return null;
        } else {
            return source.getDsUf();
        }
    }

    /*
     * (non-Javadoc)
     * @see org.dozer.DozerConverter#convertFrom(java.lang.Object, java.lang.Object)
     */
    @Override
    public UnidadeFederacao convertFrom(String source, UnidadeFederacao destination) {
    	UnidadeFederacao result = null;
        if (source == null) {
            result = null;
        } else {
        	final UnidadeFederacao unidadeFederacao = unidadeFederacaoRepository.findByUF(source);
        	if (unidadeFederacao != null){
        		result = unidadeFederacao;
        	} else {
        		result = unidadeFederacaoRepository.save(new UnidadeFederacao(source.toUpperCase()));
        	}
        }
        
        return result;
    }
}
