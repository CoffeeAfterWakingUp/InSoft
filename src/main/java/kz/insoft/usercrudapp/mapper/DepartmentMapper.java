package kz.insoft.usercrudapp.mapper;

import kz.insoft.usercrudapp.dto.DepartmentDTO;
import kz.insoft.usercrudapp.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper implements Mapper<DepartmentDTO, Department>{

    public DepartmentDTO toDto(Department department) {
        if (department == null) return null;
        return DepartmentDTO.builder()
                .id(department.getId())
                .name(department.getName())
                .build();
    }

    @Override
    public Department toEntity(DepartmentDTO departmentDTO) {
        if (departmentDTO == null) return null;
        return Department.builder()
                .id(departmentDTO.getId())
                .name(departmentDTO.getName())
                .build();
    }
}
