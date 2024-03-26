package kz.insoft.newsportal.service.impl;

import kz.insoft.newsportal.entity.Role;
import kz.insoft.newsportal.enums.Roles;
import kz.insoft.newsportal.repository.RoleRepository;
import kz.insoft.newsportal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByRole(Roles role) {
        return roleRepository.findByRole(role);
    }
}
