package com.edusasse.app.dto.converters;

import org.dozer.DozerConverter;

import com.edusasse.app.dto.enums.TipoDocumento;

public class TipoDocumentoConverter extends DozerConverter<String, TipoDocumento> {

    public TipoDocumentoConverter() {
        super(String.class, TipoDocumento.class);
    }

    @Override
    public TipoDocumento convertTo(String source, TipoDocumento destination) {
        if (source == null) {
            return null;
        } else {
            return TipoDocumento.convert(source);
        }
    }

    @Override
    public String convertFrom(TipoDocumento source, String destination) {
        if (source == null) {
            return null;
        } else {
            return TipoDocumento.convert(source);
        }
    }
}
