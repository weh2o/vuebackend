package com.joe.vuebackend.repository.spec;

import com.joe.vuebackend.domain.Course;
import com.joe.vuebackend.domain.Course_;
import com.joe.vuebackend.domain.Teacher;
import com.joe.vuebackend.domain.Teacher_;
import com.joe.vuebackend.repository.condition.CourseCondition;
import jakarta.persistence.criteria.*;
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

        // Join
        Join<Course, Teacher> teacherJoin = root.join(Course_.teacher, JoinType.LEFT);

        // 條件
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.isNotEmpty(condition.getCourseNameOrTeacher())) {
            // 課程名稱 或 老師名稱 模糊查詢，符合其一即可
            predicates.add(
                    builder.or(
                            builder.like(root.get(Course_.NAME), "%" + condition.getCourseNameOrTeacher() + "%"),
                            builder.like(teacherJoin.get(Teacher_.NAME), "%" + condition.getCourseNameOrTeacher() + "%")
                    )
            );
        }

        Predicate predicate = builder.and(predicates.toArray(new Predicate[]{}));

        // 排序
        List<Order> orders = new ArrayList<>();
        if (StringUtils.isNotEmpty(condition.getProp()) &&
                StringUtils.isNotEmpty(condition.getOrder())
        ) {
            String order = condition.getOrder().substring(0, 3);
            String prop = condition.getProp();

            if ("asc".equals(order)) {
                orders.add(builder.asc(root.get(prop)));
            } else {
                orders.add(builder.desc(root.get(prop)));
            }
        }

        return query.where(predicate).orderBy(orders).getRestriction();
    }
}
