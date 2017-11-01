package com.lish.dongfang.common.core;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;


/**
 * TODO 未开发完，暂时不用
 * @author lisong
 *
 * @param <T>
 */
public class FastSpecifications<T> implements Specification<T> {
	
	private List<Specification<T>> specifications = new ArrayList<Specification<T>>();
	
	private enum Operator{
		LK,EQ,NE,GT,LT,GTE,LTE,IN,NIN
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (!specifications.isEmpty()) {
			List<Predicate> predicates = new ArrayList<>();
            for (Specification<T> sp : specifications) {
            	predicates.add(sp.toPredicate(root, query, cb));
            }
            // 将所有条件用 and 联合起来
            if (predicates.size() > 0) {
                return cb.and(specifications.toArray(new Predicate[predicates.size()]));
            }
        }
        return cb.conjunction();
	}
	
	public static class SpecificationBuilder{
		
		public static <T> Specification<T> lk(String attr,Object value,Class<?> clazz) {
			return null;
		}
		
		public static <T> Specification<T> eq(String attr,Object value,Class<?> clazz) {
			return null;
		}
	}
	
	public class FastPredicate<T> implements Specification<T>{
		private String fieldName;
		private Comparable<?> value;
		private Class<?> clazz;
		private Operator operator;

		public FastPredicate(String fieldName, Comparable<?> value, Class<?> clazz,Operator operator) {
			this.fieldName = fieldName;
			this.value = value;
			this.clazz = clazz;
			this.operator = operator;
		}

		@Override
		public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
			Path<?> expression = null;
	        if (fieldName.contains(".")) {
	            String[] names = StringUtils.split(fieldName, ".");
	            expression = root.get(names[0]);
	            for (int i = 1; i < names.length; i++) {
	                expression = expression.get(names[i]);
	            }
	        } else {
	            expression = root.get(fieldName);
	        }


//	        switch (operator) {
//	            case EQ:
//	                return builder.equal(expression, value);
//	            case NE:
//	                return builder.notEqual(expression, value);
//	            case LK:
//	                return builder.like(expression.as(String.class), "%" + value + "%");
//	            case LT:
//	                return builder.lessThan(expression, value);
//	            case GT:
//	                return builder.greaterThan(expression, (Comparable<?>) value);
//	            case LTE:
//	                return builder.lessThanOrEqualTo(expression, (Comparable<?>) value);
//	            case GTE:
//	                return builder.greaterThanOrEqualTo(expression, (Comparable<?>) value);
//	            default:
//	                return null;
//	        }
	        return null;
		}
	}
}
