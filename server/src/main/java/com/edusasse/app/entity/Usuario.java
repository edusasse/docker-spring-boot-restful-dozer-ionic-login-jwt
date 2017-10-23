package com.edusasse.app.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_usuario")
	private Long id;

	@Column(name = "fl_ativo")
	private boolean flAtivo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_cadastro")
	private Date dhCadastro;

	@Column(name = "ds_apelido")
	private String dsApelido;

	@Column(name = "ds_perfil")
	private String dsPerfil;

	@Column(name = "ds_senha")
	private String dsSenha;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_ultima_alteracao")
	private Date dhUltimaAlteracao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_ultimo_acesso")
	private Date dtUltimoAcesso;
	
	@Version
	@Column(name = "nr_versao")
	private int nrVersao;

	// bi-directional many-to-one association to PessoaFisica
	@ManyToOne
	@JoinColumn(name = "id_pessoa")
	private PessoaFisica pessoaFisica;

	public Usuario() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFlAtivo(boolean flAtivo) {
		this.flAtivo = flAtivo;
	}

	public boolean isFlAtivo() {
		return flAtivo;
	}

	public Date getDhCadastro() {
		return this.dhCadastro;
	}

	public void setDhCadastro(Date dhCadastro) {
		this.dhCadastro = dhCadastro;
	}

	public String getDsApelido() {
		return this.dsApelido;
	}

	public void setDsApelido(String dsApelido) {
		this.dsApelido = dsApelido;
	}

	public String getDsPerfil() {
		return this.dsPerfil;
	}

	public void setDsPerfil(String dsPerfil) {
		this.dsPerfil = dsPerfil;
	}

	public String getDsSenha() {
		return this.dsSenha;
	}

	public void setDsSenha(String dsSenha) {
		this.dsSenha = dsSenha;
	}

	public Date getDtUltimoAcesso() {
		return this.dtUltimoAcesso;
	}

	public void setDtUltimoAcesso(Date dtUltimoAcesso) {
		this.dtUltimoAcesso = dtUltimoAcesso;
	}

		public PessoaFisica getPessoaFisica() {
		return this.pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public Date getDhUltimaAlteracao() {
		return this.dhUltimaAlteracao;
	}

	public void setDhUltimaAlteracao(Date dhUltimaAlteracao) {
		this.dhUltimaAlteracao = dhUltimaAlteracao;
	}

	public int getNrVersao() {
		return nrVersao;
	}
	
	public void setNrVersao(int nrVersao) {
		this.nrVersao = nrVersao;
	}

	@PreUpdate
	void onPersist() {
		this.setDhUltimaAlteracao(new Timestamp((new Date()).getTime()));
	}

	@PrePersist
	void onCreate() {
		this.setDhCadastro(new Timestamp((new Date()).getTime()));
	}

}