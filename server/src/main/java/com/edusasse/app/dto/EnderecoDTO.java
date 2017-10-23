package com.edusasse.app.dto;

import java.util.Date;

import com.edusasse.app.dto.enums.TipoEndereco;

public class EnderecoDTO {

	private int id;
	private String bairro;
	private String complemento;
	private String logradouro;
	private int numeroLogradouro;
	private String cep;
	private TipoEndereco tipoEndereco;
	private CidadeDTO cidade;
	private PessoaFisicaDTO pessoa;
	private Date dateCadastro;
	private Date dateUltimaAlteracao;
	private int versao;

	public EnderecoDTO() {
	}

	public EnderecoDTO(int id, String bairro, String complemento, String logradouro, int numeroLogradouro, String cep,
			TipoEndereco tipoEndereco, CidadeDTO cidade, PessoaFisicaDTO pessoa, Date dateCadastro, Date dateUltimaAlteracao,
			int versao) {
		super();
		this.id = id;
		this.bairro = bairro;
		this.complemento = complemento;
		this.logradouro = logradouro;
		this.numeroLogradouro = numeroLogradouro;
		this.cep = cep;
		this.tipoEndereco = tipoEndereco;
		this.cidade = cidade;
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

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumeroLogradouro() {
		return numeroLogradouro;
	}

	public void setNumeroLogradouro(int numeroLogradouro) {
		this.numeroLogradouro = numeroLogradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public TipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public CidadeDTO getCidade() {
		return cidade;
	}

	public void setCidade(CidadeDTO cidade) {
		this.cidade = cidade;
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

	public PessoaFisicaDTO getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaFisicaDTO pessoa) {
		this.pessoa = pessoa;
	}

}