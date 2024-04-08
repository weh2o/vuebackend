package com.joe.vuebackend.service.impl;

import com.joe.vuebackend.domain.Role;
import com.joe.vuebackend.repository.RoleRepository;
import com.joe.vuebackend.repository.spec.MenuSpec;
import com.joe.vuebackend.repository.spec.RoleSpec;
import com.joe.vuebackend.service.RoleService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Setter(onMethod_ = @Autowired)
    private RoleRepository roleRepository;

    @Override
    public List<Role> findByNames(List<String> names) {
        RoleSpec.RoleCondition condition = new RoleSpec.RoleCondition();
        condition.setNames(names);
        return roleRepository.findAll(RoleSpec.initSpec(condition));
    }
}
