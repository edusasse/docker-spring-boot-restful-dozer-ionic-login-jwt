package com.edusasse.app.service;

import java.util.List;

import com.edusasse.app.dto.UnidadeFederacaoDTO;

public interface UnidadeFederacaoService {
    List<UnidadeFederacaoDTO> findAll();
    void save(UnidadeFederacaoDTO userDTO);
}
