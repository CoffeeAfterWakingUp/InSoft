package kz.insoft.usercrudapp.rest;

import kz.insoft.usercrudapp.dto.AddUserDepartmentDTO;
import kz.insoft.usercrudapp.dto.DepartmentDTO;
import kz.insoft.usercrudapp.dto.ResponseDTO;
import kz.insoft.usercrudapp.entity.Department;
import kz.insoft.usercrudapp.mapper.DepartmentMapper;
import kz.insoft.usercrudapp.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentRestController {

    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentRestController(DepartmentService departmentService,
                                    DepartmentMapper departmentMapper) {
        this.departmentService = departmentService;
        this.departmentMapper = departmentMapper;
    }

    @PostMapping("/{id}")
    public boolean addUserToDepartment(@RequestBody AddUserDepartmentDTO dto,
                                    @PathVariable Long id) {
        return departmentService.addUserToDepartment(id, dto.getId());
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<DepartmentDTO>> createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        Department department = departmentMapper.toEntity(departmentDTO);
        Department newDepartment = departmentService.createAndReturn(department);
        DepartmentDTO dto = departmentMapper.toDto(newDepartment);
        HttpStatus httpStatus = HttpStatus.CREATED;
        ResponseDTO<DepartmentDTO> responseDTO = new ResponseDTO<>(httpStatus, dto, httpStatus.value());
        return new ResponseEntity<>(responseDTO, httpStatus);
    }
}
