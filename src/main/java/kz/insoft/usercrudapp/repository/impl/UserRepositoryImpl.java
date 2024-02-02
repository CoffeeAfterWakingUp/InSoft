package kz.insoft.usercrudapp.repository.impl;

import kz.insoft.usercrudapp.entity.User;
import kz.insoft.usercrudapp.repository.UserRepository;
import kz.insoft.usercrudapp.repository.db.DbManager;
import lombok.extern.slf4j.Slf4j;
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


    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DbManager.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM user");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("ID"),
                        resultSet.getString("FIRST_NAME"),
                        resultSet.getString("LAST_NAME"),
                        resultSet.getString("EMAIL"),
                        resultSet.getString("ADDRESS"),
                        resultSet.getDate("BIRTH_DATE").toLocalDate()
                );
                users.add(user);
            }
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return users;
    }

    @Override
    public User findById(Long id) {
        User user = new User();
        try (Connection connection = DbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getLong("ID"),
                        resultSet.getString("FIRST_NAME"),
                        resultSet.getString("LAST_NAME"),
                        resultSet.getString("EMAIL"),
                        resultSet.getString("ADDRESS"),
                        resultSet.getDate("BIRTH_DATE").toLocalDate()
                );
            }
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean create(User user) {
        try (Connection connection = DbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE);) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setDate(5, Date.valueOf(user.getBirthDate()));

            int rowsCount = preparedStatement.executeUpdate();
            if (rowsCount > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = DbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE);) {
            preparedStatement.setLong(1, id);
            int rowsCount = preparedStatement.executeUpdate();
            if (rowsCount > 0) {
                return true;
            }
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
            e.printStackTrace();
        }
        return false;
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

    private Long getNextId() {
        return Long.valueOf(users.size() + 1);
    }
}
