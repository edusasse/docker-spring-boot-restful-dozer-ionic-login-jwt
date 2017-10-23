package com.edusasse.app.persistence.dao.repository.specification;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.edusasse.app.web.util.SearchOperation;
import com.edusasse.app.web.util.SpecSearchCriteria;


public final class GenericSpecificationsBuilder<DO extends Serializable> {

    private final List<SpecSearchCriteria> params;

    public GenericSpecificationsBuilder() {
        params = new ArrayList<>();
    }

    // API

    @SuppressWarnings("rawtypes")
	public final GenericSpecificationsBuilder with(final String key, final String operation, final Object value, final String prefix, final String suffix) {
        return with(null, key, operation, value, prefix, suffix);
    }

    @SuppressWarnings("rawtypes")
	public final GenericSpecificationsBuilder with(final String orPredicate, final String key, final String operation, final Object value, final String prefix, final String suffix) {
        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == SearchOperation.EQUALITY) { // the operation may be complex operation
                final boolean startWithAsterisk = prefix != null && prefix.contains(SearchOperation.ZERO_OR_MORE_REGEX);
                final boolean endWithAsterisk = suffix != null && suffix.contains(SearchOperation.ZERO_OR_MORE_REGEX);

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }
            params.add(new SpecSearchCriteria(orPredicate, key, op, value));
        }
        return this;
    }

    public Specification<DO> build() {

        if (params.size() == 0)
            return null;

        Specification<DO> result = new GenericSpecification<DO>(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i)
                .isOrPredicate()
                    ? Specifications.where(result)
                        .or(new GenericSpecification<DO>(params.get(i)))
                    : Specifications.where(result)
                        .and(new GenericSpecification<DO>(params.get(i)));

        }

        return result;
    }
    
    public Specification<DO> build(Deque<?> postFixedExprStack, Function<SpecSearchCriteria, Specification<DO>> converter) {

        Deque<Specification<DO>> specStack = new LinkedList<>();

        Collections.reverse((List<?>) postFixedExprStack);

        while (!postFixedExprStack.isEmpty()) {
            Object mayBeOperand = postFixedExprStack.pop();

            if (!(mayBeOperand instanceof String)) {
                specStack.push(converter.apply((SpecSearchCriteria) mayBeOperand));
            } else {
                Specification<DO> operand1 = specStack.pop();
                Specification<DO> operand2 = specStack.pop();
                if (mayBeOperand.equals(SearchOperation.AND_OPERATOR))
                    specStack.push(Specifications.where(operand1)
                        .and(operand2));
                else if (mayBeOperand.equals(SearchOperation.OR_OPERATOR))
                    specStack.push(Specifications.where(operand1)
                        .or(operand2));
            }

        }
        return specStack.pop();

    }


    @SuppressWarnings("rawtypes")
	public final GenericSpecificationsBuilder with(GenericSpecification<DO> spec) {
        params.add(spec.getCriteria());
        return this;
    }

    @SuppressWarnings("rawtypes")
	public final GenericSpecificationsBuilder with(SpecSearchCriteria criteria) {
        params.add(criteria);
        return this;
    }
}
