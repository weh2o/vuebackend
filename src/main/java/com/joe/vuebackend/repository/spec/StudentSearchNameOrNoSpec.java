package com.joe.vuebackend.repository.spec;

import com.joe.vuebackend.domain.Student;
import com.joe.vuebackend.domain.Student_;
import com.joe.vuebackend.repository.condition.StudentCondition;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class StudentSearchNameOrNoSpec implements Specification<Student> {

    private StudentCondition condition;

    public StudentSearchNameOrNoSpec(StudentCondition condition) {
        this.condition = condition;
    }

    public static StudentSearchNameOrNoSpec initSpec(StudentCondition condition) {
        return new StudentSearchNameOrNoSpec(condition);
    }

    @Override
    public Predicate toPredicate(Root<Student> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder builder) {

        ArrayList<Predicate> predicates = new ArrayList<>();

        if (StringUtils.isNotEmpty(condition.getNameOrNo())) {
            // name 或 No 模糊查詢，符合其一即可
            predicates.add(
                    builder.or(
                            builder.like(root.get(Student_.name), "%" + condition.getNameOrNo() + "%"),
                            builder.like(root.get(Student_.no), "%" + condition.getNameOrNo() + "%")
                    )
            );
        }

        return builder.and(predicates.toArray(new Predicate[]{}));
    }
}
