package com.edusasse.app.service;

import java.util.List;

import com.edusasse.app.dto.ClienteDTO;

public interface ClienteService {
    List<ClienteDTO> findAll();
    void save(ClienteDTO userDTO);
}
