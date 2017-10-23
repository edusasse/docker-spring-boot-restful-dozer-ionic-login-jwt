package com.edusasse.app.dto.converters;

import org.dozer.DozerConverter;

import com.edusasse.app.dto.enums.TipoEndereco;

public class TipoEnderecoConverter extends DozerConverter<String, TipoEndereco> {

    public TipoEnderecoConverter() {
        super(String.class, TipoEndereco.class);
    }

    @Override
    public TipoEndereco convertTo(String source, TipoEndereco destination) {
        if (source == null) {
            return null;
        } else {
            return TipoEndereco.convert(source);
        }
    }

    @Override
    public String convertFrom(TipoEndereco source, String destination) {
        if (source == null) {
            return null;
        } else {
            return TipoEndereco.convert(source);
        }
    }
}
