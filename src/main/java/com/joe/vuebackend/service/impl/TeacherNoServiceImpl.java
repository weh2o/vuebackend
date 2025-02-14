package com.joe.vuebackend.service.impl;

import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.domain.TeacherNo;
import com.joe.vuebackend.exception.teacherNo.TeacherNoDuplicateException;
import com.joe.vuebackend.exception.teacherNo.TeacherNoIsNullException;
import com.joe.vuebackend.repository.TeacherNoRepository;
import com.joe.vuebackend.service.TeacherNoService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class TeacherNoServiceImpl implements TeacherNoService {

    @Setter(onMethod_ = @Autowired)
    private TeacherNoRepository teacherNoRepository;

    @Override
    public HttpResult<String> save(String no) {
        //教師證為空拋出異常
        if (StringUtils.isEmpty(no)){
            HttpResult.fail();
            throw new TeacherNoIsNullException();
        }

        //存在重複教師證拋出異常
        boolean exists = teacherNoRepository.existsByNo(no);
        if (exists) {
            throw new TeacherNoDuplicateException(no);
        }
        //不存在重複的，保存操作
        TeacherNo teacherNo = new TeacherNo();
        teacherNo.setNo(no);
        teacherNo.setAvailable(Boolean.TRUE);
        teacherNoRepository.save(teacherNo);
        return HttpResult.success("保存教師證:" + no + "成功");
    }
}
