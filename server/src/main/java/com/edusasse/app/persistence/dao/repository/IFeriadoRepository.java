package com.edusasse.app.persistence.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edusasse.app.entity.Feriado;

public interface IFeriadoRepository extends JpaRepository<Feriado, Integer> {

}
