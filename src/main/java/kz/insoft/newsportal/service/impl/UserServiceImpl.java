package kz.insoft.newsportal.service.impl;

import kz.insoft.newsportal.entity.Role;
import kz.insoft.newsportal.entity.User;
import kz.insoft.newsportal.enums.Roles;
import kz.insoft.newsportal.repository.UserRepository;
import kz.insoft.newsportal.service.RoleService;
import kz.insoft.newsportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;
    private RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.roleService = roleService;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username != null && !username.isEmpty()) {
            User user = userRepository.findByEmail(username);
            if (user != null && user.getId() != null) {
                return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getRoles());
            }
        }
        return null;
    }

    @Override
    public User getByEmail(String email) {
        if (email != null && !email.isEmpty()) {
            return userRepository.findByEmail(email);
        }
        return null;
    }

    @Override
    public boolean create(User user) {
        if (user != null) {
            Role role = roleService.findByRole(Roles.ROLE_USER);
            user.addRole(role);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
