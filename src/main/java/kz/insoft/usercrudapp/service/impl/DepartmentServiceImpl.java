package kz.insoft.usercrudapp.service.impl;

import kz.insoft.usercrudapp.entity.Department;
import kz.insoft.usercrudapp.entity.User;
import kz.insoft.usercrudapp.repository.DepartmentRepository;
import kz.insoft.usercrudapp.service.DepartmentService;
import kz.insoft.usercrudapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final UserService userService;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository,
                                 UserService userService) {
        this.departmentRepository = departmentRepository;
        this.userService = userService;
    }

    @Override
    public boolean addUserToDepartment(Long departmentId, Long userId) {
        if (departmentId == null || userId == null) {
            return false;
        }

        Optional<Department> optDepartment = departmentRepository.findById(departmentId);
        if (optDepartment.isPresent()) {
            User user = userService.findById(userId);
            if (user != null) {
                Department department = optDepartment.get();
                department.addUser(user);
                departmentRepository.save(department);
                return true;
            }

        }
        return false;
    }

    @Override
    public Department createAndReturn(Department department) {
        if (department == null) return null;
        return departmentRepository.save(department);
    }
}
