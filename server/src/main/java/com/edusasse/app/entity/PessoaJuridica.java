package com.edusasse.app.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the pessoa_juridica database table.
 * 
 */
@Entity
@Table(name="pessoa_juridica")
@DiscriminatorValue("J")
@NamedQuery(name="PessoaJuridica.findAll", query="SELECT p FROM PessoaJuridica p")
public class PessoaJuridica extends Pessoa {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_pessoa")
	private int idPessoa;

	@Column(name="ds_inscricao_estadual")
	private String dsInscricaoEstadual;

	@Column(name="ds_inscricao_municipal")
	private String dsInscricaoMunicipal;

	@Column(name="ds_razao_social")
	private String dsRazaoSocial;

	@Column(name="nr_cnpj")
	private BigDecimal nrCnpj;

	public PessoaJuridica() {
	}

	public int getIdPessoa() {
		return this.idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getDsInscricaoEstadual() {
		return this.dsInscricaoEstadual;
	}

	public void setDsInscricaoEstadual(String dsInscricaoEstadual) {
		this.dsInscricaoEstadual = dsInscricaoEstadual;
	}

	public String getDsInscricaoMunicipal() {
		return this.dsInscricaoMunicipal;
	}

	public void setDsInscricaoMunicipal(String dsInscricaoMunicipal) {
		this.dsInscricaoMunicipal = dsInscricaoMunicipal;
	}

	public String getDsRazaoSocial() {
		return this.dsRazaoSocial;
	}

	public void setDsRazaoSocial(String dsRazaoSocial) {
		this.dsRazaoSocial = dsRazaoSocial;
	}

	public BigDecimal getNrCnpj() {
		return this.nrCnpj;
	}

	public void setNrCnpj(BigDecimal nrCnpj) {
		this.nrCnpj = nrCnpj;
	} 

}