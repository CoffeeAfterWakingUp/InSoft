package kz.insoft.usercrudapp.service.impl;

import kz.insoft.usercrudapp.entity.User;
import kz.insoft.usercrudapp.entity.UserDetails;
import kz.insoft.usercrudapp.exception.UserNotFoundException;
import kz.insoft.usercrudapp.repository.UserRepository;
import kz.insoft.usercrudapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

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
        return userRepository.findById(id).orElse(new User());
    }

    @Override
    public boolean create(User user) {
        if (user != null) {
            if (user.getFirstName() != null && !user.getFirstName().isEmpty()) {
                if (user.getLastName() != null && !user.getLastName().isEmpty()) {
                    if (user.getEmail()!= null && !user.getEmail().isEmpty() && user.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                        if (user.getAddress() != null && !user.getAddress().isEmpty()) {
                            if (user.getBirthDate() != null && user.getBirthDate().compareTo(LocalDate.now(ZoneId.of("UTC+6"))) < 0) {
                                User newUser = userRepository.save(user);
                                return newUser.getId() != null;
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
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(User user, Long id) {
        if (user != null && id != null) {
            if (user.getId().equals(id)) {
                User updUser = userRepository.save(user);
                return updUser.getId() != null;
            }
        }
        return false;
    }

    @Override
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return user;
        }
        throw new UserNotFoundException("User with email " + email + " not found!");
    }

    @Override
    public User findByFirstNameAndLastName(String firstName, String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<User> findByAddressIn(List<String> addressList) {
        return userRepository.findByAddressIn(addressList);
    }

    @Override
    public int findUsersCount() {
        return userRepository.findUsersCount();
    }

    @Override
    public User findByBirthDateAfter(LocalDate date) {
        return userRepository.findByBirthDateAfter(date);
    }

    @Override
    public User findByBirthDateBefore(LocalDate date) {
        return userRepository.findByBirthDateBefore(date);
    }

    @Override
    public void deleteByEmail(String email) {
        userRepository.deleteByEmail(email);
    }

    @Override
    public User createAndReturn(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateAndReturn(User user, Long id) {
        if (user != null && id != null) {
            if (user.getId().equals(id)) {
                Optional<User> foundUserOpt = userRepository.findById(id);
                if (foundUserOpt.isPresent()) {
                    UserDetails userDetails = user.getUserDetails();
                    userDetails.setId(user.getId());
                    user.getPhoneList().forEach(p -> p.setUser(user));
                    return userRepository.save(user);
                }
            }
        }
        return null;
    }


    @Override
    public User updateEmail(User user, Long id) {
        if (user != null && id != null) {
            Optional<User> foundUserOpt = userRepository.findById(id);
            if (foundUserOpt.isPresent()) {
                User foundUser = foundUserOpt.get();
                if (user.getEmail() != null) {
                    foundUser.setEmail(user.getEmail());
                    return userRepository.save(foundUser);
                }
            }
        }
        return null;
    }

    @Override
    public User updatePatch(User user, Long id) {
        if (user != null && id != null) {
            Optional<User> foundUserOpt = userRepository.findById(id);
            if (foundUserOpt.isPresent()) {
                User foundUser = foundUserOpt.get();
                if (user.getEmail() != null) {
                    foundUser.setEmail(user.getEmail());
                }
                if (user.getBirthDate() != null) {
                    foundUser.setBirthDate(user.getBirthDate());
                }
                if (user.getFirstName() != null) {
                    foundUser.setFirstName(user.getFirstName());
                }
                if (user.getLastName() != null) {
                    foundUser.setLastName(user.getLastName());
                }
                if (user.getAddress() != null) {
                    foundUser.setAddress(user.getAddress());
                }

                return userRepository.save(foundUser);
            }
        }
        return null;
    }
}
