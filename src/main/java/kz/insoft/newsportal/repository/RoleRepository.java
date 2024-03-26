package kz.insoft.newsportal.repository;

import kz.insoft.newsportal.entity.Role;
import kz.insoft.newsportal.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(Roles role);
}
