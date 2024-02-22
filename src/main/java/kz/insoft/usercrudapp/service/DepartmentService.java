package kz.insoft.usercrudapp.service;

import kz.insoft.usercrudapp.entity.Department;

public interface DepartmentService {

    boolean addUserToDepartment(Long departmentId, Long userId);

    Department createAndReturn(Department department);

}
