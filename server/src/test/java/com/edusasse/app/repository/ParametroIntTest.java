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

import com.edusasse.app.dto.ParametroDTO;
import com.edusasse.app.entity.Parametro;
import com.edusasse.app.persistence.dao.repository.IParametroRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ParametroIntTest {

	@Autowired
	private IParametroRepository parametroRepository;

	@Autowired
	private DozerBeanMapper mapper;

	@Test
	public void check() {
		ParametroDTO parametroDto = new ParametroDTO();
		parametroDto.setParametro("Parametro");
		parametroDto.setValor("Test value");
		Parametro parametroEntity = mapper.map(parametroDto, Parametro.class);
		Long id = (Long) parametroRepository.save(parametroEntity).getId();
		Parametro parametroFindEntity = parametroRepository.findOne(id);
		assertEquals("Parametro", parametroDto.getParametro(), parametroFindEntity.getDsParametro());
		assertEquals("Valor", parametroDto.getValor(), parametroFindEntity.getVlParametro());
		assertNotNull("Versão", parametroFindEntity.getNrVersao());
		assertNotNull("Data Cadastro", parametroFindEntity.getDhCadastro());
		assertNull("Data Ultima Alteração null", parametroFindEntity.getDhUltimaAlteracao());
		String vlParametro = "Test value 2";
		parametroFindEntity.setVlParametro(vlParametro);
		parametroRepository.save(parametroFindEntity);
		Parametro parametroFindEntity2 = parametroRepository.findOne(id);
		assertEquals("Valor", vlParametro, parametroFindEntity2.getVlParametro());
		assertNotNull("Data Ultima Alteração", parametroFindEntity2.getDhUltimaAlteracao());
	}

}
