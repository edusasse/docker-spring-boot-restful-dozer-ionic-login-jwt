package com.edusasse.app.repository;

import static org.junit.Assert.assertEquals;

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

import com.edusasse.app.dto.PessoaFisicaDTO;
import com.edusasse.app.dto.enums.Sexo;
import com.edusasse.app.dto.enums.TipoDocumento;
import com.edusasse.app.dto.sub.DocumentoDTO;
import com.edusasse.app.dto.sub.IdentityDTO;
import com.edusasse.app.entity.PessoaFisica;
import com.edusasse.app.persistence.dao.repository.IPessoaFisicaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PessoaFisicaIntTest {

	@Autowired
	private IPessoaFisicaRepository repository;

	@Autowired
	private DozerBeanMapper mapper;

	@Test
	public void check() {
		PessoaFisicaDTO pessoaDto = new PessoaFisicaDTO();
		pessoaDto.setDataNascimento(new Date());
		pessoaDto.setDocumento(new DocumentoDTO("05949267931", TipoDocumento.CPF));
		pessoaDto.seteMail("email@email.com");
		pessoaDto.setIdentity(new IdentityDTO("Bilbos", "Baggins", Sexo.MASCULINO));
		pessoaDto.setObservacao("Test");
		PessoaFisica pessoaEntity = mapper.map(pessoaDto, PessoaFisica.class);
		Integer id = (Integer) repository.save(pessoaEntity).getIdPessoa();
		PessoaFisica pessoaEntityFind = repository.findOne(id);
		assertEquals("Primeiro Nome", pessoaDto.getIdentity().getPrimeiroNome(), pessoaEntityFind.getNmPrimeiroNome());
		assertEquals("Segundo Nome", pessoaDto.getIdentity().getPrimeiroNome(), pessoaEntityFind.getNmPrimeiroNome());
		 
		pessoaEntityFind.setNmPrimeiroNome("Bilbo");
		repository.save(pessoaEntityFind);
		PessoaFisica pessoaEntityFind2 = repository.findOne(id);
		assertEquals("Primeiro Nome", "Bilbo", pessoaEntityFind2.getNmPrimeiroNome());

	}

}
