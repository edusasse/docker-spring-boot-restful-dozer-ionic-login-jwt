package com.edusasse.app.service.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.edusasse.app.persistence.IOperationsService;
import com.google.common.collect.Lists;

public abstract class AbstractService<DO extends Serializable, ID extends Serializable> implements IOperationsService<DO, ID> {

	/*
	 * (non-Javadoc)
	 * @see com.edusasse.app.persistence.IOperations#findOne(long)
	 */
    @Override
    @Transactional(readOnly = true)
    public DO findOne(final ID id) {
        return getDao().findOne(id);
    }

    /*
     * (non-Javadoc)
     * @see com.edusasse.app.persistence.IOperations#findAll()
     */
    @Override
    @Transactional(readOnly = true)
    public List<DO> findAll() {
        return Lists.newArrayList(getDao().findAll());
    }

    @Override
    public Page<DO> findPaginated(final int page, final int size) {
        return getDao().findAll(new PageRequest(page, size));
    }

    /*
     * (non-Javadoc)
     * @see com.edusasse.app.persistence.IOperations#create(java.io.Serializable)
     */
    @Override
    public DO create(final DO entity) {
        return getDao().save(entity);
    }

    /*
     * (non-Javadoc)
     * @see com.edusasse.app.persistence.IOperations#update(java.io.Serializable)
     */
    @Override
    public DO update(final DO entity) {
        return getDao().save(entity);
    }

    /*
     * (non-Javadoc)
     * @see com.edusasse.app.persistence.IOperations#delete(java.io.Serializable)
     */
    @Override
    public void delete(final DO entity) {
        getDao().delete(entity);
    }

    /*
     * (non-Javadoc)
     * @see com.edusasse.app.persistence.IOperations#deleteById(long)
     */
    @Override
    public void deleteById(final ID entityId) {
        getDao().delete(entityId);
    }

    protected abstract PagingAndSortingRepository<DO, ID> getDao();

}