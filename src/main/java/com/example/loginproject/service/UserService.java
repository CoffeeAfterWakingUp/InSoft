package com.example.loginproject.service;

import com.example.loginproject.entity.User;
import com.example.loginproject.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (email != null) {
            User user = userRepository.findByEmail(email);
            if (user != null) {
                return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getRoles());
            }
        }
        return null;
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void create(User user) {
        userRepository.save(user);
    }
}
