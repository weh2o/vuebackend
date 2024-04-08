package com.joe.vuebackend.repository.spec;

import com.joe.vuebackend.domain.Menu;
import com.joe.vuebackend.domain.Menu_;
import com.joe.vuebackend.domain.Role;
import com.joe.vuebackend.domain.Role_;
import jakarta.persistence.criteria.*;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class MenuSpec implements Specification<Menu> {

    private MenuCondition condition;

    public MenuSpec(MenuCondition condition) {
        this.condition = condition;
    }

    public static MenuSpec initSpec(MenuCondition condition) {
        return new MenuSpec(condition);
    }

    @Override
    public Predicate toPredicate(Root<Menu> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder builder) {

        ListJoin<Menu, Role> roleJoin = root.join(Menu_.roles, JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(condition.getRoles())) {
            predicates.add(
                    builder.in(roleJoin.get(Role_.NAME)).value(condition.getRoles())
            );
        }
        return builder.and(predicates.toArray(new Predicate[]{}));
    }

    @Data
    public static class MenuCondition {
        /**
         * 角色權限英文名
         */
        List<String> roles;
    }
}
