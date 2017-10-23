package com.edusasse.app.dto.converters;

import org.dozer.DozerConverter;

import com.edusasse.app.dto.enums.Sexo;

public class SexoConverter extends DozerConverter<Character, Sexo> {

    public SexoConverter() {
        super(Character.class, Sexo.class);
    }

    @Override
    public Sexo convertTo(Character source, Sexo destination) {
        if (source == null) {
            return null;
        } else {
            return Sexo.convert(source);
        }
    }

    @Override
    public Character convertFrom(Sexo source, Character destination) {
        if (source == null) {
            return null;
        } else {
            return Sexo.convert(source);
        }
    }
}
