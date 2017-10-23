package com.edusasse.app.persistence.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.edusasse.app.entity.Parametro;

public interface IParametroRepository extends JpaRepository<Parametro, Long>, JpaSpecificationExecutor<Parametro> {

}