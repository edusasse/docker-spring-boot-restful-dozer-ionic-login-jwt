package com.edusasse.app.dto.converters;

import org.dozer.DozerConverter;

import com.edusasse.app.dto.enums.ProfileAlignmentDTO;

public class ProfileAlignmentConverter extends DozerConverter<Character, ProfileAlignmentDTO> {

    public ProfileAlignmentConverter() {
        super(Character.class, ProfileAlignmentDTO.class);
    }

    @Override
    public ProfileAlignmentDTO convertTo(Character source, ProfileAlignmentDTO destination) {
        if (source == null) {
            return null;
        } else {
            return ProfileAlignmentDTO.convert(source);
        }
    }

    @Override
    public Character convertFrom(ProfileAlignmentDTO source, Character destination) {
        if (source == null) {
            return null;
        } else {
            return ProfileAlignmentDTO.convert(source);
        }
    }
}
