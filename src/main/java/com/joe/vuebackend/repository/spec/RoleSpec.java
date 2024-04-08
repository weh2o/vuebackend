package com.joe.vuebackend.repository.spec;

import com.joe.vuebackend.domain.Role;
import com.joe.vuebackend.domain.Role_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class RoleSpec implements Specification<Role> {

    private RoleCondition condition;

    public RoleSpec(RoleCondition condition) {
        this.condition = condition;
    }

    public static RoleSpec initSpec(RoleCondition condition) {
        return new RoleSpec(condition);
    }

    @Override
    public Predicate toPredicate(Root<Role> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(condition.getNames())) {
            List<String> names = condition.getNames();
            predicates.add(
                    builder.in(root.get(Role_.NAME)).value(names)
            );
        }

        return builder.and(predicates.toArray(new Predicate[]{}));
    }

    @Data
    public static class RoleCondition {
        /**
         * 角色權限英文名
         */
        List<String> names;
    }
}
