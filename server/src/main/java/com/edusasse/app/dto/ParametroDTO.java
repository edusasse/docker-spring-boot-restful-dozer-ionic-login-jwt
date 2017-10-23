package com.edusasse.app.dto;

import java.io.Serializable;
import java.util.Date;

public class ParametroDTO implements Serializable {

	private static final long serialVersionUID = 6201551992452775680L;
	
	private Long id;
	private String parametro;
	private String valor;
	private Date dateCadastro;
	private Date dateUltimaAlteracao;
	private int versao;

	public ParametroDTO(Long id, String parametro, String valor, Date dateCadastro, Date dateUltimaAlteracao,
			int versao) {
		super();
		this.id = id;
		this.parametro = parametro;
		this.valor = valor;
		this.dateCadastro = dateCadastro;
		this.dateUltimaAlteracao = dateUltimaAlteracao;
		this.versao = versao;
	}

	public ParametroDTO() {
	}

	public final Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public final String getParametro() {
		return parametro;
	}

	public final void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public final String getValor() {
		return valor;
	}

	public final void setValor(String valor) {
		this.valor = valor;
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