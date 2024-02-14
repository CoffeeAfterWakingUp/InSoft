package kz.insoft.usercrudapp.repository;

import kz.insoft.usercrudapp.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
