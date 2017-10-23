package com.edusasse.app.service;

import java.util.List;

import com.edusasse.app.dto.EnderecoDTO;

public interface EnderecoService {
    List<EnderecoDTO> findAll();
    void save(EnderecoDTO userDTO);
}
