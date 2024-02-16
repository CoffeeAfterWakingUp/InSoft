package kz.insoft.usercrudapp.mapper;

import kz.insoft.usercrudapp.dto.UserDTO;
import kz.insoft.usercrudapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<UserDTO, User>{

    private UserDetailsMapper userDetailsMapper;
    private DepartmentMapper departmentMapper;
    private UserPhoneMapper userPhoneMapper;

    @Autowired
    public UserMapper(UserDetailsMapper userDetailsMapper, DepartmentMapper departmentMapper, UserPhoneMapper userPhoneMapper) {
        this.userDetailsMapper = userDetailsMapper;
        this.departmentMapper = departmentMapper;
        this.userPhoneMapper = userPhoneMapper;
    }

    public UserDTO toDto(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .address(user.getAddress())
                .birthDate(user.getBirthDate())
                .details(userDetailsMapper.toDto(user.getUserDetails()))
                .departments(departmentMapper.toDtoList(user.getDepartments()))
                .phones(userPhoneMapper.toDtoList(user.getPhoneList()))
                .build();
    }
}
