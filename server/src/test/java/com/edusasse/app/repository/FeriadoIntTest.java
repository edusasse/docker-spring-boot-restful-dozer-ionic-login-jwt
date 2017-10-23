package com.edusasse.app.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.edusasse.app.dto.FeriadoDTO;
import com.edusasse.app.entity.Feriado;
import com.edusasse.app.persistence.dao.repository.IFeriadoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class FeriadoIntTest {

	@Autowired
	private IFeriadoRepository feriadoRepository;

	@Autowired
	private DozerBeanMapper mapper;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	
	@Test
	public void check() throws ParseException {
		FeriadoDTO feriadoDto = new FeriadoDTO();
		feriadoDto.setDateFeriado(sdf.parse("01/01/2017"));
		Feriado feriadoEntity = mapper.map(feriadoDto, Feriado.class);
		Integer id = (Integer) feriadoRepository.save(feriadoEntity).getId();
		Feriado feriadoFindEntity = feriadoRepository.findOne(id);
		assertEquals("Data Feriado", feriadoDto.getDateFeriado(), feriadoFindEntity.getDtFeriado());
		assertNotNull("Versão", feriadoFindEntity.getNrVersao());
		assertNotNull("Data Cadastro", feriadoFindEntity.getDhCadastro());
		assertNull("Data Ultima Alteração null", feriadoFindEntity.getDhUltimaAlteracao());
		final Date nd = sdf.parse("11/11/2017");
		feriadoFindEntity.setDtFeriado(nd);
		feriadoRepository.save(feriadoFindEntity);
		Feriado feriadoFindEntity2 = feriadoRepository.findOne(id);
		assertEquals("Data Feriado 2", nd, feriadoFindEntity2.getDtFeriado());
		assertNotNull("Data Ultima Alteração", feriadoFindEntity2.getDhUltimaAlteracao());
	}

}
