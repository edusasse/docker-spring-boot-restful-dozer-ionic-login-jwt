package com.edusasse.app.persistence.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edusasse.app.entity.Cidade;

public interface ICidadeRepository extends JpaRepository<Cidade, Integer> {

}
