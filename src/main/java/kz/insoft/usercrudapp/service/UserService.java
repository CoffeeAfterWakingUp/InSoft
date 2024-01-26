package kz.insoft.usercrudapp.service;

import kz.insoft.usercrudapp.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    boolean create(User user);

    boolean delete(Long id);

    boolean update(User user, Long id);
}
