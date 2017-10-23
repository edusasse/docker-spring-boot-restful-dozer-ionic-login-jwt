package com.edusasse.app.dto.converters;

import org.dozer.DozerConverter;

import com.edusasse.app.dto.enums.SituacaoCliente;

public class SituacaoClienteConverter extends DozerConverter<Character, SituacaoCliente> {

    public SituacaoClienteConverter() {
        super(Character.class, SituacaoCliente.class);
    }

    @Override
    public SituacaoCliente convertTo(Character source, SituacaoCliente destination) {
        if (source == null) {
            return null;
        } else {
            return SituacaoCliente.convert(source);
        }
    }

    @Override
    public Character convertFrom(SituacaoCliente source, Character destination) {
        if (source == null) {
            return null;
        } else {
            return SituacaoCliente.convert(source);
        }
    }
}
