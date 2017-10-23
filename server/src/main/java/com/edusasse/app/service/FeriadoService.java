package com.edusasse.app.service;

import java.util.List;

import com.edusasse.app.dto.FeriadoDTO;

public interface FeriadoService {
    List<FeriadoDTO> findAll();
    void save(FeriadoDTO userDTO);
}
