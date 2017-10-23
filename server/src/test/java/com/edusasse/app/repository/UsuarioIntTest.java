package com.edusasse.app.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
import com.edusasse.app.dto.UsuarioDTO;
import com.edusasse.app.dto.enums.Sexo;
import com.edusasse.app.dto.enums.TipoDocumento;
import com.edusasse.app.dto.sub.DocumentoDTO;
import com.edusasse.app.dto.sub.IdentityDTO;
import com.edusasse.app.entity.PessoaFisica;
import com.edusasse.app.entity.Usuario;
import com.edusasse.app.persistence.dao.repository.IPessoaFisicaRepository;
import com.edusasse.app.persistence.dao.repository.IUsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioIntTest {

	@Autowired
	private IUsuarioRepository usuarioRepository;
	
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
		UsuarioDTO usuarioDto = new UsuarioDTO();
		usuarioDto.setApelido("frodo");
		usuarioDto.setSenha("g4nd4lf");
		usuarioDto.setPerfil("ROLE_ADMIN");
		usuarioDto.setAtivo(Boolean.TRUE);
		usuarioDto.setPessoa(preparedPessoaDto);
		Usuario usuarioEntity = mapper.map(usuarioDto, Usuario.class);
		Long id = usuarioRepository.save(usuarioEntity).getId();
		Usuario usuarioFindEntity = usuarioRepository.findOne(id);
		assertEquals("Apelido", usuarioDto.getApelido(), usuarioFindEntity.getDsApelido());
		assertEquals("Password", usuarioDto.getSenha(), usuarioFindEntity.getDsSenha());
		assertEquals("Perfil", usuarioDto.getPerfil(), usuarioFindEntity.getDsPerfil());
		assertEquals("Pessoa", usuarioDto.getPessoa().getId(), new Integer(usuarioFindEntity.getPessoaFisica().getIdPessoa()));
		assertTrue("Ativo", usuarioDto.isAtivo());
		assertNotNull("Versão", usuarioFindEntity.getNrVersao());
		assertNotNull("Data Cadastro", usuarioFindEntity.getDhCadastro());
		assertNull("Data Ultima Alteração null", usuarioFindEntity.getDhUltimaAlteracao());		
		final String dsSenha = "1234567890";
		usuarioFindEntity.setDsSenha(dsSenha);
		usuarioRepository.save(usuarioFindEntity);
		Usuario UsuarioFindEntity2 = usuarioRepository.findOne(id);
		assertEquals("Senha 2", dsSenha, UsuarioFindEntity2.getDsSenha());
		assertNotNull("Data Ultima Alteração", UsuarioFindEntity2.getDhUltimaAlteracao());
	}

}
