package kz.insoft.usercrudapp.repository.impl;

import kz.insoft.usercrudapp.entity.User;
import kz.insoft.usercrudapp.repository.UserRepository;
import kz.insoft.usercrudapp.repository.rowmapper.UserRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository {


    private static final String CREATE = "INSERT user(first_name, last_name, email, address, birth_date) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM user WHERE id=?";
    private static final String DELETE = "DELETE FROM user WHERE id=?";


    private static List<User> users = new ArrayList<>();

    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL, new UserRowMapper());
    }

    @Override
    public User findById(Long id) {
       return jdbcTemplate.queryForObject(FIND_BY_ID, new UserRowMapper(), id);
    }

    @Override
    public boolean create(User user) {
        int rows = jdbcTemplate.update(CREATE, user.getFirstName(), user.getLastName(), user.getEmail(), user.getAddress(), Date.valueOf(user.getBirthDate()));
        return rows > 0;
    }

    @Override
    public boolean delete(Long id) {
        String temp = DELETE + id;
        int rows = jdbcTemplate.update(temp);
        return rows > 0;
    }

    @Override
    public boolean update(User user, Long id) {
        User oldUser = users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(new User());
        int index = users.indexOf(oldUser);
        user.setId(oldUser.getId());

        users.set(index, user);

        return true;
    }
}
