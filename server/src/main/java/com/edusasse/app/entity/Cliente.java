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
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_cliente")
	private int id;

	@Column(name = "cd_situacao")
	private char cdSituacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_cadastro")
	private Date dhCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_ultima_alteracao")
	private Date dhUltimaAlteracao;
	
	@Column(name = "ds_observacao")
	private String dsObservacao;

	@Column(name = "fl_visto")
	private boolean flVisto;

	@Version
	@Column(name = "nr_versao")
	private int nrVersao;

	// bi-directional many-to-one association to Pessoa
	@ManyToOne
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;

	public Cliente() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public char getCdSituacao() {
		return this.cdSituacao;
	}

	public void setCdSituacao(char cdSituacao) {
		this.cdSituacao = cdSituacao;
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

	public String getDsObservacao() {
		return this.dsObservacao;
	}

	public void setDsObservacao(String dsObservacao) {
		this.dsObservacao = dsObservacao;
	}

	public void setFlVisto(boolean flVisto) {
		this.flVisto = flVisto;
	}
	
	public boolean isFlVisto() {
		return flVisto;
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

}