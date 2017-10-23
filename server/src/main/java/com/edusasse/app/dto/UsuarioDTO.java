package com.edusasse.app.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.security.crypto.password.PasswordEncoder;

public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = -5427895440292474839L;

	private Long id;
	private PessoaFisicaDTO pessoa;
	private String apelido;
	private String senha;
	private boolean ativo;
	private String perfil;
	private Date dateCadastro;
	private Date dateUltimaAlteracao;
	private int versao;

	/**
	 * Encode the password.
	 * 
	 * @param passwordEncoder
	 */
	public void encodePassword(PasswordEncoder passwordEncoder) {
		this.senha = passwordEncoder.encode(this.senha);
	}

	public UsuarioDTO() {
	}

	public UsuarioDTO(Long id, PessoaFisicaDTO pessoa, String apelido, String senha, boolean ativo, String perfil,
			Date dateCadastro, Date dateUltimaAlteracao, int versao) {
		super();
		this.id = id;
		this.pessoa = pessoa;
		this.apelido = apelido;
		this.senha = senha;
		this.ativo = ativo;
		this.perfil = perfil;
		this.dateCadastro = dateCadastro;
		this.dateUltimaAlteracao = dateUltimaAlteracao;
		this.versao = versao;
	}

	public final Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public final PessoaFisicaDTO getPessoa() {
		return pessoa;
	}

	public final void setPessoa(PessoaFisicaDTO pessoa) {
		this.pessoa = pessoa;
	}

	public final String getApelido() {
		return apelido;
	}

	public final void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public final String getSenha() {
		return senha;
	}

	public final void setSenha(String senha) {
		this.senha = senha;
	}

	public final boolean isAtivo() {
		return ativo;
	}

	public final void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public final String getPerfil() {
		return perfil;
	}

	public final void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public final Date getDateCadastro() {
		return dateCadastro;
	}

	public final void setDateCadastro(Date dateCadastro) {
		this.dateCadastro = dateCadastro;
	}

	public final Date getDateUltimaAlteracao() {
		return dateUltimaAlteracao;
	}

	public final void setDateUltimaAlteracao(Date dateUltimaAlteracao) {
		this.dateUltimaAlteracao = dateUltimaAlteracao;
	}

	public final int getVersao() {
		return versao;
	}

	public final void setVersao(int versao) {
		this.versao = versao;
	}

}