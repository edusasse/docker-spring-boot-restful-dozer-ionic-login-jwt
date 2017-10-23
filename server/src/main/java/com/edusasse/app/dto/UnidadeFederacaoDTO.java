package com.edusasse.app.dto;

import java.util.Date;

public class UnidadeFederacaoDTO {

	private int id;
	private String uf;
	private Date dateCadastro;
	private Date dateUltimaAlteracao;
	private int versao;

	public UnidadeFederacaoDTO(int id, String uf, Date dateCadastro, Date dateUltimaAlteracao, int versao) {
		super();
		this.id = id;
		this.uf = uf;
		this.dateCadastro = dateCadastro;
		this.dateUltimaAlteracao = dateUltimaAlteracao;
		this.versao = versao;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public UnidadeFederacaoDTO() {
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Date getDateCadastro() {
		return dateCadastro;
	}

	public void setDateCadastro(Date dateCadastro) {
		this.dateCadastro = dateCadastro;
	}

	public Date getDateUltimaAlteracao() {
		return dateUltimaAlteracao;
	}

	public void setDateUltimaAlteracao(Date dateUltimaAlteracao) {
		this.dateUltimaAlteracao = dateUltimaAlteracao;
	}

	public int getVersao() {
		return versao;
	}

	public void setVersao(int versao) {
		this.versao = versao;
	}

}