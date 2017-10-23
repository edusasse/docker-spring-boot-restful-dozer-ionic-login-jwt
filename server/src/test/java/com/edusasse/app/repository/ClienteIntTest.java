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

import com.edusasse.app.dto.ClienteDTO;
import com.edusasse.app.dto.PessoaFisicaDTO;
import com.edusasse.app.dto.enums.Sexo;
import com.edusasse.app.dto.enums.SituacaoCliente;
import com.edusasse.app.dto.enums.TipoDocumento;
import com.edusasse.app.dto.sub.DocumentoDTO;
import com.edusasse.app.dto.sub.IdentityDTO;
import com.edusasse.app.entity.Cliente;
import com.edusasse.app.entity.PessoaFisica;
import com.edusasse.app.persistence.dao.repository.IClienteRepository;
import com.edusasse.app.persistence.dao.repository.IPessoaFisicaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ClienteIntTest {

	@Autowired
	private IClienteRepository clienteRepository;
	
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
		ClienteDTO clienteDto = new ClienteDTO();
		clienteDto.setObservacao("Cliente esta querendo desconto familia");
		clienteDto.setSituacao(SituacaoCliente.PROSPECCAO);
		clienteDto.setPessoa(preparedPessoaDto);
		Cliente clienteEntity = mapper.map(clienteDto, Cliente.class);
		Integer id = (Integer) clienteRepository.save(clienteEntity).getId();
		Cliente clienteFindEntity = clienteRepository.findOne(id);
		assertEquals("Observação", clienteDto.getObservacao(), clienteFindEntity.getDsObservacao());
		assertEquals("Numero Cliente", clienteDto.getSituacao().getValue(), clienteFindEntity.getCdSituacao());
		assertEquals("Pessoa", clienteDto.getPessoa().getId(), preparedPessoaDto.getId());
		assertNotNull("Versão", clienteFindEntity.getNrVersao());
		assertNotNull("Data Cadastro", clienteFindEntity.getDhCadastro());
		assertNull("Data Ultima Alteração null", clienteFindEntity.getDhUltimaAlteracao());		
		clienteFindEntity.setDsObservacao("Bloqueado");
		clienteFindEntity.setCdSituacao('B');
		clienteRepository.save(clienteFindEntity);
		Cliente clienteFindEntity2 = clienteRepository.findOne(id);
		assertEquals("Observação 2", "Bloqueado", clienteFindEntity2.getDsObservacao());
		assertEquals("Situação 2", 'B', clienteFindEntity2.getCdSituacao());
		assertNotNull("Data Ultima Alteração", clienteFindEntity2.getDhUltimaAlteracao());
	}

}
