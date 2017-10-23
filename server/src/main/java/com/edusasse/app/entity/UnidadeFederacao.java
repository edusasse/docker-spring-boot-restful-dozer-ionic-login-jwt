package com.edusasse.app.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;


/**
 * The persistent class for the unidade_federacao database table.
 * 
 */
@Entity
@Table(name="unidade_federacao")
@NamedQuery(name="UnidadeFederacao.findAll", query="SELECT u FROM UnidadeFederacao u")
public class UnidadeFederacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_uf")
	private int idUf; 

	@Column(name="ds_uf")
	private String dsUf;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dh_cadastro")
	private Date dhCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dh_ultima_alteracao")
	private Date dhUltimaAlteracao;

	@Version
	@Column(name="nr_versao")
	private int nrVersao;

	//bi-directional many-to-one association to Cidade
	@OneToMany(mappedBy="unidadeFederacao")
	private List<Cidade> cidades;

	public UnidadeFederacao() {
	}

	public UnidadeFederacao(String dsUf) {
		super();
		this.dsUf = dsUf;
	}

	public int getIdUf() {
		return this.idUf;
	}

	public void setIdUf(int idUf) {
		this.idUf = idUf;
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

	public String getDsUf() {
		return this.dsUf;
	}

	public void setDsUf(String dsUf) {
		this.dsUf = dsUf;
	}

	public int getNrVersao() {
		return this.nrVersao;
	}

	public void setNrVersao(int nrVersao) {
		this.nrVersao = nrVersao;
	}

	public List<Cidade> getCidades() {
		return this.cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Cidade addCidade(Cidade cidade) {
		getCidades().add(cidade);
		cidade.setUnidadeFederacao(this);

		return cidade;
	}

	public Cidade removeCidade(Cidade cidade) {
		getCidades().remove(cidade);
		cidade.setUnidadeFederacao(null);

		return cidade;
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