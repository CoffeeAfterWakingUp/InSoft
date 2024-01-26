package kz.insoft.usercrudapp.service.impl;

import kz.insoft.usercrudapp.entity.User;
import kz.insoft.usercrudapp.repository.UserRepository;
import kz.insoft.usercrudapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean create(User user) {
        if (user != null) {
            return userRepository.create(user);
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (id != null) {
            return userRepository.delete(id);
        }
        return false;
    }

    @Override
    public boolean update(User user, Long id) {
        return false;
    }
}
