package com.edusasse.app.dto;

import java.util.Date;

import com.edusasse.app.dto.sub.DocumentoDTO;
import com.edusasse.app.dto.sub.IdentityDTO;

public class PessoaFisicaDTO extends AbstractPessoaDTO {

	private IdentityDTO identity;
	private Date dataNascimento;
	private DocumentoDTO documento;

	public IdentityDTO getIdentity() {
		return identity;
	}

	public void setIdentity(IdentityDTO identity) {
		this.identity = identity;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public DocumentoDTO getDocumento() {
		return documento;
	}

	public void setDocumento(DocumentoDTO documento) {
		this.documento = documento;
	}

}
