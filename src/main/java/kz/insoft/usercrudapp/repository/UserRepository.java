package kz.insoft.usercrudapp.repository;

import kz.insoft.usercrudapp.entity.User;

import java.util.List;

public interface UserRepository {

    String FIND_ALL = "SELECT * FROM user";

    List<User> findAll();

    User findById(Long id);

    boolean create(User user);

    boolean delete(Long id);

    boolean update(User user, Long id);

}
