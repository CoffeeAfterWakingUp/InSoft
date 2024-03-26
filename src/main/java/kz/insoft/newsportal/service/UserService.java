package kz.insoft.newsportal.service;

import kz.insoft.newsportal.entity.User;

public interface UserService {

    User getByEmail(String email);

    boolean create(User user);
}
