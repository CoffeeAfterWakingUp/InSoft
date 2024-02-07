package kz.insoft.usercrudapp.service.impl;

import kz.insoft.usercrudapp.entity.User;
import kz.insoft.usercrudapp.repository.UserRepository;
import kz.insoft.usercrudapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(@Qualifier("userRepositoryImpl2") UserRepository userRepository) {
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
            if (user.getFirstName() != null && !user.getFirstName().isEmpty()) {
                if (user.getLastName() != null && !user.getLastName().isEmpty()) {
                    if (user.getEmail()!= null && !user.getEmail().isEmpty() && user.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                        if (user.getAddress() != null && !user.getAddress().isEmpty()) {
                            if (user.getBirthDate() != null && user.getBirthDate().compareTo(LocalDate.now(ZoneId.of("UTC+6"))) < 0) {
                                return userRepository.create(user);
                            }
                        }
                    }
                }
            }
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
        if (user != null && id != null) {
            if (user.getId().equals(id)) {
                userRepository.update(user, id);
            }
        }
        return false;
    }
}
