package com.edusasse.app.dto;

import java.util.Date;

import com.edusasse.app.dto.enums.SituacaoCliente;

public class ClienteDTO {

	private int id;
	private SituacaoCliente situacao;
	private String observacao;
	private boolean visto;
	private AbstractPessoaDTO pessoa;
	private Date dateCadastro;
	private Date dateUltimaAlteracao;
	private int versao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SituacaoCliente getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoCliente situacao) {
		this.situacao = situacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public boolean isVisto() {
		return visto;
	}

	public void setVisto(boolean visto) {
		this.visto = visto;
	}

	public AbstractPessoaDTO getPessoa() {
		return pessoa;
	}

	public void setPessoa(AbstractPessoaDTO pessoa) {
		this.pessoa = pessoa;
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