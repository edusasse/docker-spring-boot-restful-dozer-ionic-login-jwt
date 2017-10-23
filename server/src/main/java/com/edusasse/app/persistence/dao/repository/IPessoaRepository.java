package com.edusasse.app.persistence.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edusasse.app.entity.Pessoa;

public interface IPessoaRepository extends JpaRepository<Pessoa, Integer> {

}