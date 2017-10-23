package com.edusasse.app.service;

import java.util.List;

import com.edusasse.app.dto.TelefoneDTO;

public interface TelefoneService {
    List<TelefoneDTO> findAll();
    TelefoneDTO findByNumeroTelefone(String numTelefone);
    void save(TelefoneDTO userDTO);
}
