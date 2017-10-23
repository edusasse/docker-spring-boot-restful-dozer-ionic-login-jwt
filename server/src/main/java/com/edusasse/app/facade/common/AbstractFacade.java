package com.edusasse.app.facade.common;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import com.edusasse.app.facade.PageDTO;
import com.edusasse.app.persistence.IOperationsFacade;
import com.edusasse.app.service.common.AbstractService;
import com.google.common.collect.Lists;
public abstract class AbstractFacade<DTO extends Serializable, DO extends Serializable, ID extends Serializable> implements IOperationsFacade<DTO, ID> {

	@SuppressWarnings("unchecked")
	public Class<DTO> getDTOClass() {
		Class<DTO> result = null;
		if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
			result = ((Class<DTO>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
		} else if (getClass().getGenericSuperclass() instanceof Class) {
			final Class<?> c = (Class<?>) getClass().getGenericSuperclass();
			result = (Class<DTO>) ((ParameterizedType) c.getGenericSuperclass()).getActualTypeArguments()[0];
		} else {
			result = (Class<DTO>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public Class<DO> getDOClass() {
		Class<DO> result = null;
		if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
			result = ((Class<DO>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
		} else if (getClass().getGenericSuperclass() instanceof Class) {
			final Class<?> c = (Class<?>) getClass().getGenericSuperclass();
			result = (Class<DO>) ((ParameterizedType) c.getGenericSuperclass()).getActualTypeArguments()[1];
		} else {
			result = (Class<DO>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
		}
		return result;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.edusasse.app.persistence.IOperationsFacade#findOne(java.io.Serializable)
	 */
    @Override
    @Transactional(readOnly = true)
    public DTO findOne(final ID id) {
        return getMapper().map(getService().findOne(id), getDTOClass());
    }

    /*
     * (non-Javadoc)
     * @see com.edusasse.app.persistence.IOperationsFacade#findAll()
     */
    @Override
    @Transactional(readOnly = true)
    public List<DTO> findAll() {
        return Lists.newArrayList(getService().findAll()).stream()
	            .map(entity -> getMapper().map(entity, getDTOClass()))
	            .collect(Collectors.toList());
    }

    /*
     * (non-Javadoc)
     * @see com.edusasse.app.persistence.IOperationsFacade#findPaginated(int, int)
     */
    @Override
    public PageDTO<DTO> findPaginated(final int page, final int size) {
    	Page<DO> pageDO = getService().findPaginated(page, size);
        return new PageDTO<DTO>(pageDO.getTotalPages(), pageDO.getContent().stream()
        		.map(entity -> getMapper().map(entity, getDTOClass()))
	            .collect(Collectors.toList()));
        
    }

    /*
     * (non-Javadoc)
     * @see com.edusasse.app.persistence.IOperations#create(java.io.Serializable)
     */
	@Override
    public DTO create(final DTO entity) {
        final DO Bo = getMapper().map(entity, getDOClass());
		final DO createdObj = getService().create(Bo);
		return getMapper().map(createdObj, getDTOClass());
    }

    /*
     * (non-Javadoc)
     * @see com.edusasse.app.persistence.IOperations#update(java.io.Serializable)
     */
    @Override
    public DTO update(final DTO entity) {
    	final DO Bo = getMapper().map(entity, getDOClass());
		final DO createdObj = getService().update(Bo);
		return getMapper().map(createdObj, getDTOClass());
    }

    /*
     * (non-Javadoc)
     * @see com.edusasse.app.persistence.IOperations#delete(java.io.Serializable)
     */
    @Override
    public void delete(final DTO entity) {
    	final DO Bo = getMapper().map(entity, getDOClass());
        getService().delete(Bo);
    }

    /*
     * (non-Javadoc)
     * @see com.edusasse.app.persistence.IOperationsFacade#deleteById(java.io.Serializable)
     */
    @Override
    public void deleteById(final ID entityId) {
        getService().deleteById(entityId);
    }

    protected abstract AbstractService<DO, ID> getService();
    
    // TODO it should be more abstract!
    protected abstract DozerBeanMapper getMapper();

}