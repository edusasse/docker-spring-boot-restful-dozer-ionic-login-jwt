package com.edusasse.app.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.dozer.DozerBeanMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.edusasse.app.dto.UnidadeFederacaoDTO;
import com.edusasse.app.entity.UnidadeFederacao;
import com.edusasse.app.persistence.dao.repository.IUnidadeFederacaoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UnidadeFederacaoIntTest {

	@Autowired
	private IUnidadeFederacaoRepository unidadeFederacaoRepository;

	@Autowired
	private DozerBeanMapper mapper;

	@Test
	public void check() {
		UnidadeFederacaoDTO unidadeFederacaoDto = new UnidadeFederacaoDTO();
		unidadeFederacaoDto.setUf("SC");
		UnidadeFederacao unidadeFederacaoEntity = mapper.map(unidadeFederacaoDto, UnidadeFederacao.class);
		Integer id = (Integer) unidadeFederacaoRepository.save(unidadeFederacaoEntity).getIdUf();
		UnidadeFederacao unidadeFederacaoFindEntity = unidadeFederacaoRepository.findOne(id);
		assertEquals("UF", unidadeFederacaoDto.getUf(), unidadeFederacaoEntity.getDsUf());
		assertNotNull("Versão", unidadeFederacaoFindEntity.getNrVersao());
		assertNotNull("Data Cadastro", unidadeFederacaoFindEntity.getDhCadastro());
		assertNull("Data Ultima Alteração null", unidadeFederacaoFindEntity.getDhUltimaAlteracao());		
		unidadeFederacaoFindEntity.setDsUf("SP");
		unidadeFederacaoRepository.save(unidadeFederacaoFindEntity);
		UnidadeFederacao cidadeFindEntity2 = unidadeFederacaoRepository.findOne(id);
		assertEquals("Nome", "SP", cidadeFindEntity2.getDsUf());
		assertNotNull("Data Ultima Alteração", cidadeFindEntity2.getDhUltimaAlteracao());
	}

}
