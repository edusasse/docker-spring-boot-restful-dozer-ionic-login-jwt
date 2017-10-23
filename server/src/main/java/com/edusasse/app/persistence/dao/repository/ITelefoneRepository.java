package com.edusasse.app.persistence.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edusasse.app.entity.Telefone;

public interface ITelefoneRepository extends JpaRepository<Telefone, Integer> {
	@Query("SELECT t FROM Telefone t WHERE t.nrTelefone = ?#{[0]}")
	Telefone findByNumeroTelefone(String nrTelefone);
}
