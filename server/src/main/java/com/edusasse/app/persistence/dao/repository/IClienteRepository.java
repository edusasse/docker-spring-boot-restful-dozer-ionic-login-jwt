package com.edusasse.app.persistence.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edusasse.app.entity.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Integer> {

}
