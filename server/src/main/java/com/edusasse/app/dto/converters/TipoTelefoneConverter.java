package com.edusasse.app.dto.converters;

import org.dozer.DozerConverter;

import com.edusasse.app.dto.enums.TipoTelefone;

public class TipoTelefoneConverter extends DozerConverter<String, TipoTelefone> {

    public TipoTelefoneConverter() {
        super(String.class, TipoTelefone.class);
    }

    @Override
    public TipoTelefone convertTo(String source, TipoTelefone destination) {
        if (source == null) {
            return null;
        } else {
            return TipoTelefone.convert(source);
        }
    }

    @Override
    public String convertFrom(TipoTelefone source, String destination) {
        if (source == null) {
            return null;
        } else {
            return TipoTelefone.convert(source);
        }
    }
}
