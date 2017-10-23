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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;


/**
 * The persistent class for the cidade database table.
 * 
 */
@Entity
@NamedQuery(name="Cidade.findAll", query="SELECT c FROM Cidade c")
public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_cidade")
	private int idCidade;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dh_cadastro")
	private Date dhCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dh_ultima_alteracao")
	private Date dhUltimaAlteracao;

	@Column(name="nm_cidade")
	private String nmCidade;

	@Version
	@Column(name="nr_versao")
	private int nrVersao;

	//bi-directional many-to-one association to UnidadeFederacao
	@ManyToOne
	@JoinColumn(name="id_uf")
	private UnidadeFederacao unidadeFederacao;

	//bi-directional many-to-one association to Endereco
	@OneToMany(mappedBy="cidade")
	private List<Endereco> enderecos;

	public Cidade() {
	}

	public int getIdCidade() {
		return this.idCidade;
	}

	public void setIdCidade(int idCidade) {
		this.idCidade = idCidade;
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

	public String getNmCidade() {
		return this.nmCidade;
	}

	public void setNmCidade(String nmCidade) {
		this.nmCidade = nmCidade;
	}

	public int getNrVersao() {
		return this.nrVersao;
	}

	public void setNrVersao(int nrVersao) {
		this.nrVersao = nrVersao;
	}

	public UnidadeFederacao getUnidadeFederacao() {
		return this.unidadeFederacao;
	}

	public void setUnidadeFederacao(UnidadeFederacao unidadeFederacao) {
		this.unidadeFederacao = unidadeFederacao;
	}

	public List<Endereco> getEnderecos() {
		return this.enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Endereco addEndereco(Endereco endereco) {
		getEnderecos().add(endereco);
		endereco.setCidade(this);

		return endereco;
	}

	public Endereco removeEndereco(Endereco endereco) {
		getEnderecos().remove(endereco);
		endereco.setCidade(null);

		return endereco;
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