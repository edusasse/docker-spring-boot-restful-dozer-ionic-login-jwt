package com.edusasse.app.persistence.dao.repository.specification;

import java.io.Serializable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.edusasse.app.web.util.SpecSearchCriteria;

public class GenericSpecification<DO extends Serializable> implements Specification<DO> {

	private SpecSearchCriteria criteria;

	public GenericSpecification(final SpecSearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}

	public SpecSearchCriteria getCriteria() {
		return criteria;
	}

	@Override
	public Predicate toPredicate(final Root<DO> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
		switch (criteria.getOperation()) {
		case EQUALITY:
			return builder.equal(root.get(criteria.getKey()), criteria.getValue());
		case NEGATION:
			return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
		case GREATER_THAN:
			return builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
		case LESS_THAN:
			return builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
		case LIKE:
			return builder.like(root.get(criteria.getKey()), criteria.getValue().toString());
		case STARTS_WITH:
			return builder.like(root.get(criteria.getKey()), criteria.getValue() + "%");
		case ENDS_WITH:
			return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue());
		case CONTAINS:
			return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
		default:
			return null;
		}
	}

}
