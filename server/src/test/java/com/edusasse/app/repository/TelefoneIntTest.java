package com.edusasse.app.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

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

import com.edusasse.app.dto.PessoaFisicaDTO;
import com.edusasse.app.dto.TelefoneDTO;
import com.edusasse.app.dto.enums.Sexo;
import com.edusasse.app.dto.enums.TipoDocumento;
import com.edusasse.app.dto.enums.TipoTelefone;
import com.edusasse.app.dto.sub.DocumentoDTO;
import com.edusasse.app.dto.sub.IdentityDTO;
import com.edusasse.app.entity.PessoaFisica;
import com.edusasse.app.entity.Telefone;
import com.edusasse.app.persistence.dao.repository.IPessoaFisicaRepository;
import com.edusasse.app.persistence.dao.repository.ITelefoneRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TelefoneIntTest {

	@Autowired
	private ITelefoneRepository telefoneRepository;
	
	@Autowired
	private IPessoaFisicaRepository pessoaRepository;
	
	@Autowired
	private DozerBeanMapper mapper;

	private PessoaFisicaDTO preparedPessoaDto;

	@Before
	public void preparePessoa() {
		preparedPessoaDto = new PessoaFisicaDTO();
		preparedPessoaDto.setDataNascimento(new Date());
		preparedPessoaDto.setDocumento(new DocumentoDTO("05949267931", TipoDocumento.CPF));
		preparedPessoaDto.seteMail("email@email.com");
		preparedPessoaDto.setIdentity(new IdentityDTO("Bilbos", "Baggins", Sexo.MASCULINO));
		preparedPessoaDto.setObservacao("Test");
		PessoaFisica pessoaEntity = mapper.map(preparedPessoaDto, PessoaFisica.class);
		Integer id = (Integer) pessoaRepository.save(pessoaEntity).getIdPessoa();
		preparedPessoaDto.setId(id);
	}
	
	@Test
	public void check() {
		TelefoneDTO telefoneDto = new TelefoneDTO();
		telefoneDto.setNomeContato("Amigo");
		telefoneDto.setNumeroTelefone("+55 47 99999-9999");
		telefoneDto.setTipoTelefone(TipoTelefone.CELULAR);
		telefoneDto.setPessoa(preparedPessoaDto);
		Telefone telefoneEntity = mapper.map(telefoneDto, Telefone.class);
		Integer id = (Integer) telefoneRepository.save(telefoneEntity).getIdTelefone();
		Telefone telefoneFindEntity = telefoneRepository.findOne(id);
		assertEquals("Nome Contato", telefoneDto.getNomeContato(), telefoneFindEntity.getNmPessoaContato());
		assertEquals("Numero Telefone", telefoneDto.getNumeroTelefone(), telefoneFindEntity.getNrTelefone());
		assertEquals("Tipo Telefone", telefoneDto.getNumeroTelefone(), telefoneFindEntity.getNrTelefone());
		assertNotNull("Versão", telefoneFindEntity.getNrVersao());
		assertNotNull("Data Cadastro", telefoneFindEntity.getDhCadastro());
		assertNull("Data Ultima Alteração null", telefoneFindEntity.getDhUltimaAlteracao());		
		telefoneFindEntity.setNrTelefone("+55 48 88888-8888");
		telefoneRepository.save(telefoneFindEntity);
		Telefone telefoneFindEntity2 = telefoneRepository.findOne(id);
		assertEquals("Numero do Telefone", "+55 48 88888-8888", telefoneFindEntity2.getNrTelefone());
		assertNotNull("Data Ultima Alteração", telefoneFindEntity2.getDhUltimaAlteracao());
	}

}
