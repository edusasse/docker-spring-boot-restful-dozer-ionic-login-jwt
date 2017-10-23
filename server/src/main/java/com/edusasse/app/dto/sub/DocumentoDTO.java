package com.edusasse.app.dto.sub;

import com.edusasse.app.dto.enums.TipoDocumento;

public class DocumentoDTO {
    private String documento;
    private TipoDocumento tipoDocumento;

    public DocumentoDTO(String documento, TipoDocumento tipoDocumento) {
        this.documento = documento;
        this.tipoDocumento = tipoDocumento;
    }

    public DocumentoDTO() {
    }
    
	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
   
}