package com.edusasse.app.persistence;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;

public interface IOperationsService<DO extends Serializable, K> {

	DO findOne(final K id);

	List<DO> findAll();

	Page<DO> findPaginated(int page, int size);

	DO create(final DO entity);

	DO update(final DO entity);

	void delete(final DO entity);

	void deleteById(final K entityId);

}