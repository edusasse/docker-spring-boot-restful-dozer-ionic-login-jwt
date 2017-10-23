package com.edusasse.app.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.edusasse.app.dto.CidadeDTO;
import com.edusasse.app.dto.UnidadeFederacaoDTO;
import com.edusasse.app.entity.Cidade;
import com.edusasse.app.entity.UnidadeFederacao;
import com.edusasse.app.persistence.dao.repository.ICidadeRepository;
import com.edusasse.app.persistence.dao.repository.IUnidadeFederacaoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CidadeIntTest {

	@Autowired
	private ICidadeRepository cidadeRepository;
	
	@Autowired
	private IUnidadeFederacaoRepository unidadeFederacaoRepository;

	@Autowired
	private DozerBeanMapper mapper;

	@Before
	public void prepareState(){
		UnidadeFederacaoDTO unidadeFederacaoDto = new UnidadeFederacaoDTO();
		unidadeFederacaoDto.setUf("SC");
		UnidadeFederacao unidadeFederacaoEntity = mapper.map(unidadeFederacaoDto, UnidadeFederacao.class);
		unidadeFederacaoRepository.save(unidadeFederacaoEntity).getIdUf();
	}
	
	@Test
	public void check() {
		CidadeDTO cidadeDto = new CidadeDTO();
		cidadeDto.setNome("Massarandubas");
		cidadeDto.setUf("SC");
		Cidade cidadeEntity = mapper.map(cidadeDto, Cidade.class);
		Integer id = (Integer) cidadeRepository.save(cidadeEntity).getIdCidade();
		Cidade cidadeFindEntity = cidadeRepository.findOne(id);
		assertEquals("Nome", cidadeDto.getNome(), cidadeFindEntity.getNmCidade());
		assertEquals("UF", cidadeDto.getUf(), cidadeFindEntity.getUnidadeFederacao().getDsUf());
		assertNotNull("Versão", cidadeFindEntity.getNrVersao());
		assertNotNull("Data Cadastro", cidadeFindEntity.getDhCadastro());
		assertNull("Data Ultima Alteração null", cidadeFindEntity.getDhUltimaAlteracao());		
		cidadeFindEntity.setNmCidade("Massaranduba");
		cidadeRepository.save(cidadeFindEntity);
		Cidade cidadeFindEntity2 = cidadeRepository.findOne(id);
		assertEquals("Nome", "Massaranduba", cidadeFindEntity2.getNmCidade());
		assertNotNull("Data Ultima Alteração", cidadeFindEntity2.getDhUltimaAlteracao());
	}

}
