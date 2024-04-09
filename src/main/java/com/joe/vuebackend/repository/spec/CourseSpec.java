package com.joe.vuebackend.repository.spec;

import com.joe.vuebackend.domain.Course;
import com.joe.vuebackend.domain.Student;
import com.joe.vuebackend.domain.Student_;
import com.joe.vuebackend.repository.condition.CourseCondition;
import com.joe.vuebackend.repository.condition.StudentCondition;
import jakarta.persistence.criteria.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CourseSpec implements Specification<Course> {

    private CourseCondition condition;

    public CourseSpec(CourseCondition condition) {
        this.condition = condition;
    }

    public static CourseSpec initSpec(CourseCondition condition) {
        return new CourseSpec(condition);
    }

    @Override
    public Predicate toPredicate(Root<Course> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder builder) {

        // 條件
        List<Predicate> predicates = new ArrayList<>();


        Predicate predicate = builder.and(predicates.toArray(new Predicate[]{}));

        // 排序
        List<Order> orders = new ArrayList<>();
//        if (StringUtils.isNotEmpty(condition.getProp()) &&
//                StringUtils.isNotEmpty(condition.getOrder())
//        ) {
//            String order = condition.getOrder().substring(0, 3);
//            String prop = condition.getProp();
//            // 性別特別處理
//            if ("sex".equals(prop)) {
//                prop = Student_.GENDER;
//            }
//            if ("asc".equals(order)) {
//                orders.add(builder.asc(root.get(prop)));
//            } else {
//                orders.add(builder.desc(root.get(prop)));
//            }
//        }

        // 默認排序: AGE
        if (ObjectUtils.isEmpty(orders)) {
            orders.add(builder.asc(root.get(Student_.AGE)));
        }

        return query.where(predicate).orderBy(orders).getRestriction();
    }
}
