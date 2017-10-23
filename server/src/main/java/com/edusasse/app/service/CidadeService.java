package com.edusasse.app.service;

import java.util.List;

import com.edusasse.app.dto.CidadeDTO;

public interface CidadeService {
    List<CidadeDTO> findAll();
    void save(CidadeDTO userDTO);
}
