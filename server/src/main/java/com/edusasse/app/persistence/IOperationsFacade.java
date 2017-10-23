package com.edusasse.app.persistence;

import java.io.Serializable;
import java.util.List;

import com.edusasse.app.facade.PageDTO;

public interface IOperationsFacade<DTO extends Serializable, ID extends Serializable> {

	DTO findOne(final ID id);

	List<DTO> findAll();

	PageDTO<DTO> findPaginated(int page, int size);

	DTO create(final DTO entity);

	DTO update(final DTO entity);

	void delete(final DTO entity);

	void deleteById(final ID entityId);

}