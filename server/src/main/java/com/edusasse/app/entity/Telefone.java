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
 * The persistent class for the telefone database table.
 * 
 */
@Entity
@NamedQuery(name = "Telefone.findAll", query = "SELECT t FROM Telefone t")
public class Telefone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_telefone")
	private int idTelefone;

	@Column(name = "nm_pessoa_contato")
	private String nmPessoaContato;

	@Column(name = "nr_telefone")
	private String nrTelefone;

	@Column(name = "ds_tipo_telefone")
	private String dsTipoTelefone;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_cadastro")
	private Date dhCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_ultima_alteracao")
	private Date dhUltimaAlteracao;

	@Version
	@Column(name = "nr_versao")
	private int nrVersao;

	// bi-directional many-to-one association to Pessoa
	@ManyToOne
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;

	public Telefone() {
	}

	public int getIdTelefone() {
		return this.idTelefone;
	}

	public void setIdTelefone(int idTelefone) {
		this.idTelefone = idTelefone;
	}

	public Date getDhCadastro() {
		return this.dhCadastro;
	}

	public void setDhCadastro(Date dhCadastro) {
		this.dhCadastro = dhCadastro;
	}

	public Date getDhUltimaAlteracao() {
		return this.dhUltimaAlteracao;
	}

	public void setDhUltimaAlteracao(Date dhUltimaAlteracao) {
		this.dhUltimaAlteracao = dhUltimaAlteracao;
	}

	public String getNmPessoaContato() {
		return this.nmPessoaContato;
	}

	public void setNmPessoaContato(String nmPessoaContato) {
		this.nmPessoaContato = nmPessoaContato;
	}

	public String getNrTelefone() {
		return this.nrTelefone;
	}

	public void setNrTelefone(String nrTelefone) {
		this.nrTelefone = nrTelefone;
	}

	public int getNrVersao() {
		return this.nrVersao;
	}

	public void setNrVersao(int nrVersao) {
		this.nrVersao = nrVersao;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@PreUpdate
	void onPersist() {
		this.setDhUltimaAlteracao(new Timestamp((new Date()).getTime()));
	}

	@PrePersist
	void onCreate() {
		this.setDhCadastro(new Timestamp((new Date()).getTime()));
	}

	public String getDsTipoTelefone() {
		return dsTipoTelefone;
	}

	public void setDsTipoTelefone(String dsTipoTelefone) {
		this.dsTipoTelefone = dsTipoTelefone;
	}

}