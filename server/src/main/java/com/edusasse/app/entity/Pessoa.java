package com.edusasse.app.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * The persistent class for the pessoa database table.
 * 
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name = "id_tipo_pessoa")
@Table(name = "Pessoa")
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_pessoa")
	private int idPessoa;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_cadastro")
	private Date dhCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dh_ultima_alteracao")
	private Date dhUltimaAlteracao;

	@Column(name = "ds_email")
	private String dsEmail;

	@Column(name = "ds_email_faturamento")
	private String dsEmailFaturamento;

	@Column(name = "ds_observacao")
	private String dsObservacao;

	@Version
	@Column(name = "nr_versao")
	private int nrVersao;

	// bi-directional many-to-one association to Cliente
	@OneToOne(mappedBy = "pessoa", fetch = FetchType.EAGER)
	private Cliente cliente;

	// bi-directional many-to-one association to Endereco
	@OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
	private List<Endereco> enderecos;

	// bi-directional many-to-one association to Telefone
	@OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY)
	private List<Telefone> telefones;

	public Pessoa() {
	}

	public int getIdPessoa() {
		return this.idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
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

	public String getDsEmail() {
		return this.dsEmail;
	}

	public void setDsEmail(String dsEmail) {
		this.dsEmail = dsEmail;
	}

	public String getDsEmailFaturamento() {
		return this.dsEmailFaturamento;
	}

	public void setDsEmailFaturamento(String dsEmailFaturamento) {
		this.dsEmailFaturamento = dsEmailFaturamento;
	}

	public String getDsObservacao() {
		return this.dsObservacao;
	}

	public void setDsObservacao(String dsObservacao) {
		this.dsObservacao = dsObservacao;
	}

	public int getNrVersao() {
		return this.nrVersao;
	}

	public void setNrVersao(int nrVersao) {
		this.nrVersao = nrVersao;
	}

	public List<Endereco> getEnderecos() {
		return this.enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Endereco addEndereco(Endereco endereco) {
		getEnderecos().add(endereco);
		endereco.setPessoa(this);

		return endereco;
	}

	public Endereco removeEndereco(Endereco endereco) {
		getEnderecos().remove(endereco);
		endereco.setPessoa(null);

		return endereco;
	}

	public List<Telefone> getTelefones() {
		return this.telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Telefone addTelefone(Telefone telefone) {
		getTelefones().add(telefone);
		telefone.setPessoa(this);

		return telefone;
	}

	public Telefone removeTelefone(Telefone telefone) {
		getTelefones().remove(telefone);
		telefone.setPessoa(null);

		return telefone;
	}

	@PreUpdate
	void onPersist() {
		this.setDhUltimaAlteracao(new Timestamp((new Date()).getTime()));
	}

	@PrePersist
	void onCreate() {
		this.setDhCadastro(new Timestamp((new Date()).getTime()));
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}