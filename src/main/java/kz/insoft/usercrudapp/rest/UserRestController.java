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

    @PutMapping("/{id}")
    public UserDTO updateUser(@RequestBody UserDTO userDTO,
                              @PathVariable Long id) {
        User user = userMapper.toEntity(userDTO);
        User newUser = userService.updateAndReturn(user, id);
        return userMapper.toDto(newUser);
    }

    @PatchMapping("/updEmail/{id}")
    public ResponseEntity<ResponseDTO<UserDTO>> updateEmail(@RequestBody UserDTO userDTO,
                                                            @PathVariable Long id) {
        User user = userMapper.toEntity(userDTO);
        User updUser = userService.updateEmail(user, id);
        UserDTO dto = userMapper.toDto(updUser);
        ResponseDTO<UserDTO> responseDTO = new ResponseDTO<>(HttpStatus.OK, dto, HttpStatus.OK.value());
        return ResponseEntity.ok(responseDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDTO<UserDTO>> updatePatch(@RequestBody UserDTO userDTO,
                                                            @PathVariable Long id) {
        User user = userMapper.toEntity(userDTO);
        User updUser = userService.updatePatch(user, id);
        UserDTO dto = userMapper.toDto(updUser);
        ResponseDTO<UserDTO> responseDTO = new ResponseDTO<>(HttpStatus.OK, dto, HttpStatus.OK.value());
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Boolean>> deleteUser(@PathVariable Long id) {
        Boolean delete = userService.delete(id);
        ResponseDTO<Boolean> dto = new ResponseDTO<>(HttpStatus.OK, delete, HttpStatus.OK.value());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
