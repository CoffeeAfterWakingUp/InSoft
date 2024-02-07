package kz.insoft.usercrudapp.repository.rowmapper;

import kz.insoft.usercrudapp.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return User.builder()
                .id(resultSet.getLong("ID"))
                .firstName(resultSet.getString("FIRST_NAME"))
                .lastName(resultSet.getString("LAST_NAME"))
                .address(resultSet.getString("ADDRESS"))
                .email(resultSet.getString("EMAIL"))
                .birthDate(resultSet.getDate("BIRTH_DATE").toLocalDate())
                .build();
    }
}
