package com.edusasse.app.persistence.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.edusasse.app.entity.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {

	@Query("SELECT u FROM Usuario u WHERE u.dsApelido = ?#{[0]}")
	Usuario retrieveByName(String userName);
}
