package com.edusasse.app.dto;

import java.util.Date;

public abstract class AbstractPessoaDTO {

	private Integer id;
	private String eMail;
	private String observacao;
	private Date dateCadastro;
	private Date dateUltimaAlteracao;
	private int versao;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
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