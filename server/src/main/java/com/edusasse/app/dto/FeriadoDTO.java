package com.edusasse.app.dto;

import java.util.Date;

public class FeriadoDTO {

	private int id;
	private Date dateFeriado;
	private Date dateCadastro;
	private Date dateUltimaAlteracao;
	private int versao;

	public FeriadoDTO(int id, Date dateFeriado, Date dateCadastro, Date dateUltimaAlteracao, int versao) {
		super();
		this.id = id;
		this.dateFeriado = dateFeriado;
		this.dateCadastro = dateCadastro;
		this.dateUltimaAlteracao = dateUltimaAlteracao;
		this.versao = versao;
	}

	public FeriadoDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateFeriado() {
		return dateFeriado;
	}

	public void setDateFeriado(Date dateFeriado) {
		this.dateFeriado = dateFeriado;
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