package com.edusasse.app.dto;

import java.util.Date;

import com.edusasse.app.dto.enums.TipoTelefone;

public class TelefoneDTO {

	private int id;
	private String numeroTelefone;
	private TipoTelefone tipoTelefone;
	private String nomeContato;
	private AbstractPessoaDTO pessoa;
	private Date dateCadastro;
	private Date dateUltimaAlteracao;
	private int versao;

	public TelefoneDTO() {
	}

	public TelefoneDTO(int id, String numeroTelefone, TipoTelefone tipoTelefone, String nomeContato,
			PessoaFisicaDTO pessoa, Date dateCadastro, Date dateUltimaAlteracao, int versao) {
		super();
		this.id = id;
		this.numeroTelefone = numeroTelefone;
		this.tipoTelefone = tipoTelefone;
		this.nomeContato = nomeContato;
		this.pessoa = pessoa;
		this.dateCadastro = dateCadastro;
		this.dateUltimaAlteracao = dateUltimaAlteracao;
		this.versao = versao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	public TipoTelefone getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(TipoTelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	public String getNomeContato() {
		return nomeContato;
	}

	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
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

	public AbstractPessoaDTO getPessoa() {
		return pessoa;
	}

	public void setPessoa(AbstractPessoaDTO pessoa) {
		this.pessoa = pessoa;
	}

}