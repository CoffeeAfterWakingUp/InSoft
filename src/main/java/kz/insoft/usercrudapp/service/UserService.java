package kz.insoft.usercrudapp.service;

import kz.insoft.usercrudapp.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    boolean create(User user);

    User createAndReturn(User user);

    boolean delete(Long id);

    boolean update(User user, Long id);

    User updateAndReturn(User user, Long id);

    User findByEmail(String email);

    User findByFirstNameAndLastName(String firstName, String lastName);

    List<User> findByAddressIn(List<String> addressList);

    int findUsersCount();

    User findByBirthDateAfter(LocalDate date);

    User findByBirthDateBefore(LocalDate date);

    void deleteByEmail(String email);
}
