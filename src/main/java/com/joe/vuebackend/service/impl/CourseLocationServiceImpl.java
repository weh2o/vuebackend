package com.joe.vuebackend.service.impl;

import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.domain.CourseLocation;
import com.joe.vuebackend.repository.CourseLocationRepository;
import com.joe.vuebackend.service.CourseLocationService;
import com.joe.vuebackend.vo.CourseLocationVo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseLocationServiceImpl implements CourseLocationService {

    @Setter(onMethod_ = @Autowired)
    private CourseLocationRepository locationRepository;

    @Override
    public HttpResult<List<CourseLocationVo>> findAll() {
        List<CourseLocation> list = locationRepository.findAll();
        List<CourseLocationVo> vos = list.stream().map(CourseLocationVo::of).toList();
        return HttpResult.success(vos);
    }
}
