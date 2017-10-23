package com.edusasse.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the pessoa_fisica database table.
 * 
 */
@Entity
@Table(name = "pessoa_fisica")
@DiscriminatorValue("F")
@NamedQuery(name = "PessoaFisica.findAll", query = "SELECT p FROM PessoaFisica p")
public class PessoaFisica extends Pessoa {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_pessoa")
	private int idPessoa;

	@Column(name = "nm_primeiro_nome")
	private String nmPrimeiroNome;

	@Column(name = "nm_segundo_nome")
	private String nmSegundoNome;

	@Column(name = "cd_sexo")
	private char cdSexo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_nascimento")
	private Date dtNascimento;

	@Column(name = "ds_documento")
	private String dsDocumento;

	@Column(name = "ds_tipo_documento")
	private String dsTipoDocumento;

	@OneToOne(mappedBy = "pessoaFisica")
	private Usuario usuario;

	public PessoaFisica() {
	}

	public int getIdPessoa() {
		return this.idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public char getCdSexo() {
		return this.cdSexo;
	}

	public void setCdSexo(char cdSexo) {
		this.cdSexo = cdSexo;
	}

	public Date getDtNascimento() {
		return this.dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNmPrimeiroNome() {
		return nmPrimeiroNome;
	}

	public void setNmPrimeiroNome(String nmPrimeiroNome) {
		this.nmPrimeiroNome = nmPrimeiroNome;
	}

	public String getNmSegundoNome() {
		return nmSegundoNome;
	}

	public void setNmSegundoNome(String nmSegundoNome) {
		this.nmSegundoNome = nmSegundoNome;
	}

	public String getDsDocumento() {
		return dsDocumento;
	}

	public void setDsDocumento(String dsDocumento) {
		this.dsDocumento = dsDocumento;
	}

	public String getDsTipoDocumento() {
		return dsTipoDocumento;
	}

	public void setDsTipoDocumento(String dsTipoDocumento) {
		this.dsTipoDocumento = dsTipoDocumento;
	}

}