package kz.insoft.usercrudapp.rest;

import kz.insoft.usercrudapp.dto.ResponseDTO;
import kz.insoft.usercrudapp.dto.UserDTO;
import kz.insoft.usercrudapp.entity.User;
import kz.insoft.usercrudapp.mapper.UserMapper;
import kz.insoft.usercrudapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    private UserService userService;
    private UserMapper userMapper;


    @Autowired
    public UserRestController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userMapper.toDtoList(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<UserDTO>> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        UserDTO userDTO = userMapper.toDto(user);
        HttpStatus httpStatus = HttpStatus.OK;
        ResponseDTO<UserDTO> responseDTO = new ResponseDTO<>(httpStatus, userDTO, httpStatus.value());
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/email/{email}")
    public UserDTO getUserByEmail(@PathVariable String email) {
        User user = userService.findByEmail(email);
        return userMapper.toDto(user);
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        User newUser = userService.createAndReturn(user);
        return userMapper.toDto(newUser);
    }

}
