package com.edusasse.app.persistence.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edusasse.app.entity.Endereco;

public interface IEnderecoRepository extends JpaRepository<Endereco, Integer> {

}
