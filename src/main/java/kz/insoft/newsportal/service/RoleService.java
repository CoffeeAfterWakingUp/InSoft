package kz.insoft.newsportal.service;

import kz.insoft.newsportal.entity.Role;
import kz.insoft.newsportal.enums.Roles;

public interface RoleService {

    Role findByRole(Roles role);

}
