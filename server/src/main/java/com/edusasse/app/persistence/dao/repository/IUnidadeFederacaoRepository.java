package com.edusasse.app.persistence.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edusasse.app.entity.UnidadeFederacao;

public interface IUnidadeFederacaoRepository extends JpaRepository<UnidadeFederacao, Integer> {

	@Query("SELECT u FROM UnidadeFederacao u WHERE u.dsUf = ?#{[0]}")
	UnidadeFederacao findByUF(String uf);
}
