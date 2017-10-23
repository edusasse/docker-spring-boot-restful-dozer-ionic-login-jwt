package com.edusasse.app.repository;

import static org.junit.Assert.*;

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

import com.edusasse.app.dto.CidadeDTO;
import com.edusasse.app.dto.EnderecoDTO;
import com.edusasse.app.dto.PessoaFisicaDTO;
import com.edusasse.app.dto.enums.Sexo;
import com.edusasse.app.dto.enums.TipoDocumento;
import com.edusasse.app.dto.enums.TipoEndereco;
import com.edusasse.app.dto.sub.DocumentoDTO;
import com.edusasse.app.dto.sub.IdentityDTO;
import com.edusasse.app.entity.Endereco;
import com.edusasse.app.entity.PessoaFisica;
import com.edusasse.app.persistence.dao.repository.IEnderecoRepository;
import com.edusasse.app.persistence.dao.repository.IPessoaFisicaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class EnderecoIntTest {

	@Autowired
	private IEnderecoRepository enderecoRepository;
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
		EnderecoDTO enderecoDto = new EnderecoDTO();
		enderecoDto.setBairro("Centro");
		enderecoDto.setCep("89108000");
		enderecoDto.setCidade(new CidadeDTO(-1, "Massaranduba", "SC", null, null, 0));
		enderecoDto.setComplemento("Casa");
		enderecoDto.setLogradouro("Av. 7 de Setembro");
		enderecoDto.setNumeroLogradouro(1234);
		enderecoDto.setTipoEndereco(TipoEndereco.RESIDENCIAL);
		enderecoDto.setPessoa(this.preparedPessoaDto);
		Endereco enderecoEntity = mapper.map(enderecoDto, Endereco.class);
		Integer id = (Integer) enderecoRepository.save(enderecoEntity).getIdEndereco();
		Endereco enderecoFindEntity = enderecoRepository.findOne(id);
		assertEquals("Bairro", enderecoDto.getBairro(), enderecoEntity.getDsBairro());
		assertEquals("CEP", enderecoDto.getCep(), enderecoEntity.getDsCep());
		assertNotEquals("Cidade", -1, enderecoEntity.getCidade().getIdCidade());
		assertEquals("Complemento", enderecoDto.getComplemento(), enderecoEntity.getDsComplemento());
		assertEquals("Logradouro", enderecoDto.getLogradouro(), enderecoEntity.getDsLogradouro());
		assertEquals("Numero Logradouro", enderecoDto.getNumeroLogradouro(), enderecoEntity.getNrLogradouro());
		assertNotNull("Versão", enderecoFindEntity.getNrVersao());
		assertNotNull("Data Cadastro", enderecoFindEntity.getDhCadastro());
		assertNull("Data Ultima Alteração null", enderecoFindEntity.getDhUltimaAlteracao());
		enderecoFindEntity.setDsComplemento("Apartamento");
		enderecoRepository.save(enderecoFindEntity);
		Endereco cidadeFindEntity2 = enderecoRepository.findOne(id);
		assertEquals("Complemento 2", "Apartamento", cidadeFindEntity2.getDsComplemento());
		assertNotNull("Data Ultima Alteração", cidadeFindEntity2.getDhUltimaAlteracao());
	}

}
