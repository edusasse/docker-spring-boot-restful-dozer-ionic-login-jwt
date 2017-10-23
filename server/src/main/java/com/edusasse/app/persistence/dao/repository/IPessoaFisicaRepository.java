package com.edusasse.app.persistence.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.edusasse.app.entity.PessoaFisica;

public interface IPessoaFisicaRepository extends JpaRepository<PessoaFisica, Integer>, JpaSpecificationExecutor<PessoaFisica> {

}
