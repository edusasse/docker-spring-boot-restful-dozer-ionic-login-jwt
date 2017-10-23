package com.edusasse.app.dto.converters;

import org.dozer.DozerConverter;

import com.edusasse.app.dto.enums.SituacaoFinanceira;

public class SituacaoFinanceiraConverter extends DozerConverter<Character, SituacaoFinanceira> {

    public SituacaoFinanceiraConverter() {
        super(Character.class, SituacaoFinanceira.class);
    }

    @Override
    public SituacaoFinanceira convertTo(Character source, SituacaoFinanceira destination) {
        if (source == null) {
            return null;
        } else {
            return SituacaoFinanceira.convert(source);
        }
    }

    @Override
    public Character convertFrom(SituacaoFinanceira source, Character destination) {
        if (source == null) {
            return null;
        } else {
            return SituacaoFinanceira.convert(source);
        }
    }
}
