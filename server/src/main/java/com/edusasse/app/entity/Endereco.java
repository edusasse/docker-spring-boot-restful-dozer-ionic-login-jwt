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
 * The persistent class for the endereco database table.
 * 
 */
@Entity
@NamedQuery(name="Endereco.findAll", query="SELECT e FROM Endereco e")
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_endereco")
	private int idEndereco;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dh_cadastro")
	private Date dhCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dh_ultima_alteracao")
	private Date dhUltimaAlteracao;

	@Column(name="ds_bairro")
	private String dsBairro;

	@Column(name="ds_complemento")
	private String dsComplemento;

	@Column(name="ds_logradouro")
	private String dsLogradouro;

	@Column(name="ds_cep")
	private String dsCep;

	@Column(name="nr_logradouro")
	private int nrLogradouro;

	@Column(name="fl_tipo_endereco")
	private String flTipoEndereco;
	
	@Version
	@Column(name="nr_versao")
	private int nrVersao;

	//bi-directional many-to-one association to Cidade
	@ManyToOne
	@JoinColumn(name="id_cidade")
	private Cidade cidade;

	//bi-directional many-to-one association to Pessoa
	@ManyToOne
	@JoinColumn(name="id_pessoa")
	private Pessoa pessoa;

	public Endereco() {
	}

	public int getIdEndereco() {
		return this.idEndereco;
	}

	public void setIdEndereco(int idEndereco) {
		this.idEndereco = idEndereco;
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

	public String getDsBairro() {
		return this.dsBairro;
	}

	public void setDsBairro(String dsBairro) {
		this.dsBairro = dsBairro;
	}

	public String getDsComplemento() {
		return this.dsComplemento;
	}

	public void setDsComplemento(String dsComplemento) {
		this.dsComplemento = dsComplemento;
	}

	public String getDsLogradouro() {
		return this.dsLogradouro;
	}

	public void setDsLogradouro(String dsLogradouro) {
		this.dsLogradouro = dsLogradouro;
	}

	 
	public int getNrLogradouro() {
		return this.nrLogradouro;
	}

	public void setNrLogradouro(int nrLogradouro) {
		this.nrLogradouro = nrLogradouro;
	}

	public int getNrVersao() {
		return this.nrVersao;
	}

	public void setNrVersao(int nrVersao) {
		this.nrVersao = nrVersao;
	}

	public Cidade getCidade() {
		return this.cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
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

	public String getFlTipoEndereco() {
		return flTipoEndereco;
	}

	public void setFlTipoEndereco(String flTipoEndereco) {
		this.flTipoEndereco = flTipoEndereco;
	}

	public String getDsCep() {
		return dsCep;
	}

	public void setDsCep(String dsCep) {
		this.dsCep = dsCep;
	}
}